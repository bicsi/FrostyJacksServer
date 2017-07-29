package com.bicsi.scm


import com.bicsi.scm.utils.io.BinaryReader
import com.bicsi.scm.utils.io.BinaryWriter
import org.junit.Test

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.util.Random

import org.junit.Assert.assertArrayEquals

/**
 * Integration tests for the BinaryReader
 * and BinaryWriter classes

 * Assures that the read and written byte arrays coincide
 */
class BinaryIOTest {

    @Throws(IOException::class)
    private fun testReadWrite(length: Int) {
        val bytesToWrite = ByteArray(length)
        Random().nextBytes(bytesToWrite)


        val bos = ByteArrayOutputStream(10 * length)
        val writer = BinaryWriter(bos)

        writer.writeBinary(bytesToWrite)

        val bis = ByteArrayInputStream(bos.toByteArray())
        val reader = BinaryReader(bis)

        val bytesRead = reader.readBinary()

        assertArrayEquals(bytesToWrite, bytesRead)

        reader.close()
        writer.close()
    }

    @Test fun `Read-write test (huge)`() = testReadWrite(1024 * 1024)

    @Test fun `Read-write test (medium)`() = testReadWrite(10 * 1024)

    @Test fun `Read-write test (tiny)`() = testReadWrite(1)
}