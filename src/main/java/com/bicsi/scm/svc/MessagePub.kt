package com.bicsi.scm.svc

import com.bicsi.scm.models.proto.Messages
import com.bicsi.scm.models.proto.Messages.GenericClientMessage as GCM

/**
 * Handles interactions with the socket connections
 * via a publisher-subscriber architecture
 */
object MessagePub {
    data class Listener(val type: GCM.Type, val callback: (GCM) -> Any)
    private val subscriberList = ArrayList<Listener>()

    @Synchronized fun subscribe(type: GCM.Type,
                  callback: (GCM) -> Any) {
        subscriberList.add(Listener(type, callback))
    }

    @Synchronized fun notify(message: Messages.GenericClientMessage) {
        subscriberList
                .filter { it.type == message.type }
                .forEach { it.callback(message) }
    }
}
