package com.bicsi.scm.utils.io

import java.io.*


/**
 * Simple binary writer
 */
class BinaryWriter(os: OutputStream) : Closeable {
    private val os: DataOutputStream = DataOutputStream(os)

    @Throws(IOException::class)
    fun writeBinary(bytes: ByteArray) {
        val len = bytes.size
        os.writeInt(len)
        os.write(bytes)
        os.flush()
    }

    @Throws(IOException::class)
    override fun close() = os.close()
}
