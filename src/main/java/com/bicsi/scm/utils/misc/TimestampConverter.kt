package com.bicsi.scm.utils.misc

import com.google.protobuf.Timestamp

/**
 * Created by lucianbicsi on 29/07/2017.
 */
object TimestampConverter {
    fun millisToTimestamp(millis: Long): Timestamp = Timestamp.newBuilder()
            .setSeconds(millis / 1000)
            .setNanos(((millis % 1000) * 1000000).toInt())
            .build()

    fun timestampToMillis(timestamp: Timestamp): Long =
            timestamp.seconds * 1000 + timestamp.nanos / 1000
}