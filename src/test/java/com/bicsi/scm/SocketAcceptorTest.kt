package com.bicsi.scm

import com.bicsi.scm.utils.network.SocketAcceptor
import com.bicsi.scm.utils.network.SocketConnector
import org.junit.Test

import java.io.IOException
import java.util.concurrent.atomic.AtomicInteger

import org.junit.Assert.assertEquals

/**
 * Integration tests for the SocketAcceptor
 * class
 *
 * Asserts that listeners are being called at all times
 */
class SocketAcceptorTest {

    @Throws(IOException::class)
    @Test fun `Accept connection thread-safety test`() {
        val port = 12873
        val connectionCount = 3000
        val listenerCount = 3

        // Start an acceptor on a random port
        val acceptor = SocketAcceptor(port)
        val acceptedCount = AtomicInteger(0)
        repeat(listenerCount) { acceptor.addSocketAcceptedListener({
            acceptedCount.incrementAndGet() }) }

        // Start a number of threads connecting to the server socket
        val t = Thread(acceptor)
        t.start()
        repeat(connectionCount){
            SocketConnector("localhost", port).connect().close() }
        t.interrupt()

        assertEquals(connectionCount * listenerCount, acceptedCount.toInt())

    }
}
