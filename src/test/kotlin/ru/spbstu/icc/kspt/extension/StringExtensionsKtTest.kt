package ru.spbstu.icc.kspt.extension

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class StringExtensionsKtTest {

    @Test
    fun shouldCapitalizeAllLowercaseString() {
        val result = capitalize("hello")
        assertEquals("Hello", result)
    }

    @Test
    fun shouldNotChangeAllUppercaseString() {
        val result = capitalize("WORLD")
        assertEquals("WORLD", result)
    }

    @Test
    fun shouldNotChangeStringStartingWithUppercaseLetter() {
        val result = capitalize("HelloWorld")
        assertEquals("HelloWorld", result)
    }

    @Test
    fun shouldCapitalizeStringStartingWithLowercaseLetter() {
        val result = capitalize("testTEST")
        assertEquals("TestTEST", result)
    }

    @Test
    fun shouldReturnEmptyStringForEmptyInput() {
        val result = capitalize("")
        assertEquals("", result)
    }
}

