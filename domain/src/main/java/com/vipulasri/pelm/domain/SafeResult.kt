package com.vipulasri.pelm.domain

/**
 * Created by Vipul Asri on 29/01/22.
 */

sealed class SafeResult<out T> {

    data class Success<T>(val data: T) : SafeResult<T>()
    data class Failure(
        val exception: Exception? = Exception("Unknown Error"),
        val message: String = exception?.localizedMessage ?: ""
    ) : SafeResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success -> "Success[data=$data]"
            is Failure -> "Failure[exception=$exception]"
        }
    }
}

fun <T> SafeResult<T>.handleResult(
    onSuccess: ((result: T) -> Unit)? = null,
    onError: ((result: SafeResult.Failure) -> Unit)? = null,
) {
    when (this) {
        is SafeResult.Success -> onSuccess?.invoke(this.data)
        is SafeResult.Failure -> onError?.invoke(this)
    }
}