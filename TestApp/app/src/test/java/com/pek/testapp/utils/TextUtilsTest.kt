package com.pek.testapp.utils

import org.junit.Test
import org.junit.Assert.*


class TextUtilsTest {

    @Test
    fun checkUppingFirstLetter() {
        assertEquals("Test", TextUtils.upFirstLetter("teST"))
    }

}