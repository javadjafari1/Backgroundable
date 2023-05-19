package ir.thatsmejavad.backgroundable.core

import retrofit2.Response

fun <T> Response<T>.bodyOrException(): T {
    return if (isSuccessful) {
        body()!!
    } else {
        throw ServerException(
            code = code(),
            error = errorBody().toString()
        )
    }
}
