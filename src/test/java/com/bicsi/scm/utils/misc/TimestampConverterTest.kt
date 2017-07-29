package com.bicsi.scm.utils.misc

import com.google.protobuf.Timestamp
import org.junit.Assert.*
import org.junit.Test

/**
 * Tests for the TimestampConverter class
 */
class TimestampConverterTest {
    @Test fun `Converter test`() {
        val millis = System.currentTimeMillis()
        val timestamp = TimestampConverter.millisToTimestamp(millis)
        assertEquals(millis, TimestampConverter.timestampToMillis(timestamp))
    }
}