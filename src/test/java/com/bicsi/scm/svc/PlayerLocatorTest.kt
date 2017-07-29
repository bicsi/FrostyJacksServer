package com.bicsi.scm.svc

import com.bicsi.scm.models.proto.Geo
import com.bicsi.scm.models.proto.Messages
import com.bicsi.scm.models.proto.Messages.GenericClientMessage
import com.bicsi.scm.utils.misc.TimestampConverter
import junit.framework.Assert.assertEquals
import org.junit.Test


class PlayerLocatorTest {
    /**
     * Creates a message to notify MessagePub and asserts that
     * values are being updated in the locator
     */
    @Test fun `Location update test`() {
        val senderId = "def_user"
        val latitude = 1230981.1231
        val longitude = 12398712.1231

        PlayerLocator.init()

        MessagePub.notify(GenericClientMessage.newBuilder()
                .setType(GenericClientMessage.Type.POSITION_UPDATE)
                .setSenderId(senderId)
                .setTimestamp(TimestampConverter.millisToTimestamp(
                        System.currentTimeMillis()
                ))
                .setPositionUpdate(Messages.PositionUpdateMessage.newBuilder()
                        .setPosition(Geo.Position.newBuilder()
                                .setLatitude(latitude)
                                .setLongitude(longitude)))
                .build())

        val position = PlayerLocator.locate(senderId)!!
        assertEquals(latitude, position.latitude, 1.0e-9)
        assertEquals(longitude, position.longitude, 1.0e-9)
    }

}