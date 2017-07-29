package com.bicsi.scm.utils.network

import java.io.Closeable
import java.io.IOException
import java.net.Socket

/**
 * A class that connects through sockets to an IP and port
 */
class SocketConnector @Throws(IOException::class)
constructor(val ip: String, val port: Int) : Closeable {
    var socket: Socket? = null

    fun connect(): SocketConnector {
        socket = Socket(ip, port)
        return this
    }

    override fun close() = socket!!.close()
}
