package com.bicsi.scm.svc

import com.bicsi.scm.models.proto.Messages.GenericClientMessage
import com.bicsi.scm.svc.MessagePub.subscribe
import com.bicsi.scm.utils.misc.TimestampConverter
import java.util.*
import java.util.concurrent.ConcurrentHashMap

/**
 * Created by lucianbicsi on 29/07/2017.
 */
object PlayerLocator {
    init { init() }

    data class Location(val latitude: Double, val longitude: Double, val lastUpdateTime: Date)

    private val locationMap = ConcurrentHashMap<String, Location>()

    fun init() {
        MessagePub.subscribe(GenericClientMessage.Type.POSITION_UPDATE, { handleLocationUpdate(it) })
    }

    fun locate(playerId: String): Location? = locationMap[playerId]

    private fun handleLocationUpdate(message: GenericClientMessage) {
        val millis = TimestampConverter.timestampToMillis(message.timestamp!!)
        val positionUpdate = message.positionUpdate!!

        locationMap[message.senderId] = Location(
                positionUpdate.position.latitude,
                positionUpdate.position.longitude,
                Date(millis))
    }


}
