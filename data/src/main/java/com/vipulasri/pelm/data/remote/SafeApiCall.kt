package com.vipulasri.pelm.data.remote

import android.util.Log
import com.vipulasri.pelm.domain.SafeResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

/**
 * Created by Vipul Asri on 29/01/22.
 */

suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher,
    apiCall: suspend () -> T
): SafeResult<T> {
    return withContext(dispatcher) {
        try {
            SafeResult.Success(apiCall.invoke())
        } catch (exception: Exception) {
            Log.e("safeApiCall", exception.message.toString())
            SafeResult.Failure(exception)
        }
    }
}