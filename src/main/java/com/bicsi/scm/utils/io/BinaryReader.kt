package com.bicsi.scm.utils.io

import java.io.Closeable
import java.io.DataInputStream
import java.io.IOException
import java.io.InputStream


/**
 * Simple binary reader
 */
class BinaryReader(inputStream: InputStream) : Closeable {
    private val dis: DataInputStream = DataInputStream(inputStream)

    @Throws(IOException::class)
    fun readBinary(): ByteArray {
        val len = dis.readInt()
        val bytes = ByteArray(len)
        dis.readFully(bytes)

        return bytes
    }

    @Throws(IOException::class)
    override fun close() {
        dis.close()
    }
}
