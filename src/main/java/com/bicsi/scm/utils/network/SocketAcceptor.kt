package com.bicsi.scm.utils.network

import java.io.IOException
import java.net.ServerSocket
import java.net.Socket

/**
 * Repeatedly loops through connections
 * using sockets
 */
class SocketAcceptor @Throws(IOException::class)
constructor(port: Int) : Runnable {
    private val listeners = ArrayList<(Socket) -> Any>()
    private val serverSocket = ServerSocket(port)

    fun addSocketAcceptedListener(listener: (Socket) -> Any) {
        listeners.add(listener)
    }

    private fun fireSocketAccepted(socket: Socket) =
            listeners.forEach { l -> l(socket) }

    @Throws(Exception::class)
    override fun run(): Unit {
        while (true) fireSocketAccepted(serverSocket.accept())
    }
}
