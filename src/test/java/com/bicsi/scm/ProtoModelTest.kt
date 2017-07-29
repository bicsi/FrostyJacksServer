package com.bicsi.scm

import com.bicsi.scm.models.proto.Items
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

/**
 * Various tests around ProtoModels and integration
 * with input-output implemented
 */
class ProtoModelTest {
    @Test fun `CollectibleItem conversion test`() {
        var id = "test_item_xxx"
        var name = "Test item"
        var description = "This is a test item for protobuf"

        val collItem = Items.CollectibleItem.newBuilder()
                .setId(id)
                .setDescription(description)
                .setName(name)
                .build()

        val bytes = collItem.toByteArray()

        val collItemBack = Items.CollectibleItem.parseFrom(bytes)
        assertEquals(collItem, collItemBack)
    }
}
