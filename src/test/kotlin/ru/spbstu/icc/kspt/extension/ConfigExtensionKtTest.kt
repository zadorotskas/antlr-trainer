package ru.spbstu.icc.kspt.extension

import io.ktor.server.config.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

internal class ConfigExtensionKtTest {
    @Test
    fun shouldReturnUserDirWhenPropertyIsMissing() {
        val configMock: ApplicationConfig = mock {}

        whenever(configMock.propertyOrNull("data.lessonsFolder")).thenReturn(null)

        System.setProperty("user.dir", "/mock/user/dir")

        val result = configMock.lessonsPath

        assertEquals("/mock/user/dir/data/lessons", result)

        System.clearProperty("user.dir")
    }
    
    @Test
    fun shouldReturnLessonPathWhenAvailable() {
        val configMock: ApplicationConfig = mock {}
        val propertyMock: ApplicationConfigValue = mock {}

        whenever(configMock.propertyOrNull("data.lessonsFolder")).thenReturn(propertyMock)
        whenever(propertyMock.getString()).thenReturn("/custom/lessons")

        System.setProperty("user.dir", "/mock/user/dir")

        val result = configMock.lessonsPath

        assertEquals("/mock/user/dir/custom/lessons", result)

        System.clearProperty("user.dir")
    }

    @Test
    fun shouldReturnDefaultLessonPathWhenConfigIsNull() {
        System.setProperty("user.dir", "/mock/user/dir")

        val result = null.lessonsPath

        assertEquals("/mock/user/dir/data/lessons", result)

        System.clearProperty("user.dir")
    }

    @Test
    fun testsPathShouldReturnUserDirWhenPropertyIsMissing() {
        val configMock = mock<ApplicationConfig>()

        whenever(configMock.propertyOrNull("data.testsFolder")).thenReturn(null)

        System.setProperty("user.dir", "/mock/user/dir")

        val result = configMock.testsPath

        assertEquals("/mock/user/dir/data/tests", result)

        System.clearProperty("user.dir")
    }

    @Test
    fun shouldReturnPropertyWhenAvailable() {
        val configMock = mock<ApplicationConfig>()
        val propertyMock: ApplicationConfigValue = mock {}

        whenever(configMock.propertyOrNull("data.testsFolder")).thenReturn(propertyMock)
        whenever(propertyMock.getString()).thenReturn("/custom/tests")

        System.setProperty("user.dir", "/mock/user/dir")

        val result = configMock.testsPath

        assertEquals("/mock/user/dir/custom/tests", result)

        System.clearProperty("user.dir")
    }

    @Test
    fun shouldReturnDefaultPathWhenConfigIsNull() {
        System.setProperty("user.dir", "/mock/user/dir")

        val result = null.testsPath

        assertEquals("/mock/user/dir/data/tests", result)

        System.clearProperty("user.dir")
    }

    @Test
    fun shouldReturnClientIdWhenPropertyIsAvailable() {
        val configMock = mock<ApplicationConfig>()
        val propertyValueMock = mock<ApplicationConfigValue>()

        whenever(configMock.propertyOrNull("ktor.deployment.clientId")).thenReturn(propertyValueMock)
        whenever(propertyValueMock.getString()).thenReturn("client123")

        val result = configMock.oauthClientId

        assertEquals("client123", result)
    }

    @Test
    fun shouldThrowErrorWhenClientIdPropertyIsMissing() {
        val configMock = mock<ApplicationConfig>()

        whenever(configMock.propertyOrNull("ktor.deployment.clientId")).thenReturn(null)

        val exception = assertThrows(IllegalStateException::class.java) {
            configMock.oauthClientId
        }

        assertEquals("missing clientId in config", exception.message)
    }

    @Test
    fun shouldThrowErrorWhenConfigForClientIdIsNull() {
        val result = assertThrows(IllegalStateException::class.java) {
            null.oauthClientId
        }

        assertEquals("missing clientId in config", result.message)
    }

    @Test
    fun shouldReturnClientSecretWhenPropertyIsAvailable() {
        val configMock = mock<ApplicationConfig>()
        val propertyValueMock = mock<ApplicationConfigValue>()

        whenever(configMock.propertyOrNull("ktor.deployment.clientSecret")).thenReturn(propertyValueMock)
        whenever(propertyValueMock.getString()).thenReturn("client123")

        val result = configMock.oauthClientSecret

        assertEquals("client123", result)
    }

    @Test
    fun shouldThrowErrorWhenClientSecretPropertyIsMissing() {
        val configMock = mock<ApplicationConfig>()

        whenever(configMock.propertyOrNull("ktor.deployment.clientSecret")).thenReturn(null)

        val exception = assertThrows(IllegalStateException::class.java) {
            configMock.oauthClientSecret
        }

        assertEquals("missing clientSecret in config", exception.message)
    }

    @Test
    fun shouldThrowErrorWhenConfigForClientSecretIsNull() {
        val result = assertThrows(IllegalStateException::class.java) {
            null.oauthClientSecret
        }

        assertEquals("missing clientSecret in config", result.message)
    }

    @Test
    fun shouldReturnAntlrLibPathWhenPropertyIsAvailable() {
        val configMock = mock<ApplicationConfig>()
        val propertyValueMock = mock<ApplicationConfigValue>()

        whenever(configMock.propertyOrNull("build.antlrLibPath")).thenReturn(propertyValueMock)
        whenever(propertyValueMock.getString()).thenReturn("libPath")

        val result = configMock.antlrLibPath

        assertEquals("libPath", result)
    }

    @Test
    fun shouldThrowErrorWhenAntlrLibPathPropertyIsMissing() {
        val configMock = mock<ApplicationConfig>()

        whenever(configMock.propertyOrNull("ktor.deployment.antlrLibPath")).thenReturn(null)

        val exception = assertThrows(IllegalStateException::class.java) {
            configMock.antlrLibPath
        }

        assertEquals("missing antlrLibPath in config", exception.message)
    }

    @Test
    fun shouldThrowErrorWhenConfigForAntlrLibPathIsNull() {
        val result = assertThrows(IllegalStateException::class.java) {
            null.antlrLibPath
        }

        assertEquals("missing antlrLibPath in config", result.message)
    }

    @Test
    fun shouldReturnUrlWhenPropertyIsAvailable() {
        val configMock = mock<ApplicationConfig>()
        val propertyValueMock = mock<ApplicationConfigValue>()

        whenever(configMock.propertyOrNull("ktor.deployment.url")).thenReturn(propertyValueMock)
        whenever(propertyValueMock.getString()).thenReturn("https://example.com")

        val result = configMock.url

        assertEquals("https://example.com", result)
    }

    @Test
    fun shouldReturnDefaultUrlWhenPropertyIsMissing() {
        val configMock = mock<ApplicationConfig>()

        whenever(configMock.propertyOrNull("ktor.deployment.url")).thenReturn(null)

        val result = configMock.url

        assertEquals("http://localhost:8080", result)
    }

    @Test
    fun shouldReturnDefaultUrlWhenConfigIsNull() {
        val result = null.url

        assertEquals("http://localhost:8080", result)
    }
}
