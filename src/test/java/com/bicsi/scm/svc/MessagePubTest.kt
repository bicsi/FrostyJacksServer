package com.bicsi.scm.svc

import com.bicsi.scm.models.proto.Messages.GenericClientMessage.*
import org.junit.Assert.*
import org.junit.Test
import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.thread

/**
 * Created by lucianbicsi on 29/07/2017.
 */
class MessagePubTest {

    @Test fun `Subscribe & notify thread safety test`() {
        val checkNo = AtomicInteger(0)
        val count = 100

        (1..count).map { thread(start = true) { MessagePub.subscribe(
                            Type.POSITION_UPDATE,
                            { checkNo.incrementAndGet() }) }}
                  .forEach{ it.join() }

        (1..count).map { thread(start = true) { MessagePub.notify(getDefaultInstance()) }}
                  .forEach { it.join() }

        repeat(50) { if (checkNo.toInt() == count * count) return else Thread.sleep(100) }

        fail()
    }
}