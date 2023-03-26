package ru.spbstu.icc.kspt.build

import java.io.BufferedInputStream
import java.io.File
import java.io.FileOutputStream
import java.util.jar.*

class JarBuilder : AutoCloseable {
    private val manifest: Manifest = Manifest()
    private lateinit var jarOutputStream: JarOutputStream

    init {
        manifest.mainAttributes[Attributes.Name.MANIFEST_VERSION] = "1.0"
    }

    fun setMainClass(mainClass: String) {
        if (mainClass != "") {
            manifest.mainAttributes[Attributes.Name.MAIN_CLASS] = mainClass
        }
    }

    fun openJar(jarFile: File) {
        jarOutputStream = JarOutputStream(FileOutputStream(jarFile.toString()), manifest)
    }

    fun add(source: File, root: File) {
        val rootPathString = root.toString()
        var substringLength = rootPathString.length
        if (rootPathString.endsWith(File.pathSeparator)) {
            substringLength++
        }

        var name = source.toString().substring(substringLength).replace("\\", "/")
        if (name.startsWith("/")) {
            name = name.substring(1)
        }
        if (source.isDirectory) {
            if (!name.endsWith("/")) {
                name += "/"
            }
            val entry = JarEntry(name)
            entry.time = source.lastModified()
            jarOutputStream.putNextEntry(entry)
            jarOutputStream.closeEntry()
            val nestedFiles = source.listFiles() ?: return
            for (nestedFile in nestedFiles) {
                add(nestedFile, root)
            }
        } else if (name.endsWith(".class")) {
            val entry = JarEntry(name)
            entry.time = source.lastModified()
            jarOutputStream.putNextEntry(entry)
            BufferedInputStream(source.inputStream()).use { bufferedInputStream ->
                val buffer = ByteArray(1024)
                while (true) {
                    val count = bufferedInputStream.read(buffer)
                    if (count == -1) break
                    jarOutputStream.write(buffer, 0, count)
                }
                jarOutputStream.closeEntry()
            }
        }
    }

    fun addJar(path: File) {
        if (!this::jarOutputStream.isInitialized) {
            throw IllegalStateException("Jar Output Stream was not initialized. Call 'openJar' before 'addJar'")
        }

        val jarFile = JarFile(path.toString())

        val entries = jarFile.entries()
        while (entries.hasMoreElements()) {
            val entry = entries.nextElement()
            if (entry.name.startsWith("META-INF")) {
                continue
            }

            jarFile.getInputStream(entry).use { entryInputStream ->
                jarOutputStream.putNextEntry(entry)
                val buffer = ByteArray(4096)
                var bytesRead: Int
                while (entryInputStream.read(buffer).also { bytesRead = it } != -1) {
                    jarOutputStream.write(buffer, 0, bytesRead)
                }
                entryInputStream.close()
            }
            jarOutputStream.closeEntry()
        }
    }

    override fun close() {
        jarOutputStream.close()
    }
}