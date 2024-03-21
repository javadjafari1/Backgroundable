package ir.thatsmejavad.backgroundable.data.datastore

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import androidx.datastore.preferences.protobuf.InvalidProtocolBufferException
import ir.thatsmejavad.backgroundable.UserPref
import java.io.InputStream
import java.io.OutputStream

object UserPreferencesSerializer : Serializer<UserPref> {
    override val defaultValue: UserPref = UserPref.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): UserPref {
        try {
            return UserPref.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(
        t: UserPref,
        output: OutputStream
    ) {
        t.writeTo(output)
    }
}
