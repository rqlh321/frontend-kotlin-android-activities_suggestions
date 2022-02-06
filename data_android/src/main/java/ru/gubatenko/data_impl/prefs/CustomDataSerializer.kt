package ru.gubatenko.data_impl.prefs

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import androidx.datastore.preferences.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

object CustomDataSerializer : Serializer<CustomData> {
    override val defaultValue: CustomData = CustomData.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): CustomData {
        try {
            return CustomData.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: CustomData, output: OutputStream) = t.writeTo(output)
}
