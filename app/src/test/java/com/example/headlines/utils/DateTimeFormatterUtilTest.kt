package com.example.headlines.utils

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.Test


class DateTimeFormatterUtilTest {

    @Test
    fun `testFormatDateTime() - when valid date`() {
        //WHEN
        val result = DateTimeFormatterUtil.formatDateTime("2020-01-23T13:00:00Z")

        //THEN
        assertThat(result, IsEqual("2020-01-23"))
    }

    @Test
    fun `testFormatDateTime - when invalid datetime`() {
        //WHEN
        val result = DateTimeFormatterUtil.formatDateTime("invalid")

        //THEN
        assertThat(result, IsEqual(" "))
    }

}