package com.polishone.animalblog.common.utils

import android.util.Log
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response
import java.lang.StringBuilder

abstract class SafeApiRequest {

    val TAG = "SAFE_API_REQUEST"

    suspend fun <T: Any> safeApiRequest(call: suspend () -> Response<T>): T {
        val response = call.invoke()
        if (response.isSuccessful){
            return response.body()!!
        } else {
            val responseErr = response.errorBody()?.string()
            val message = StringBuilder()
            responseErr.let {
                try {
                    message.append(JSONObject(it).getString("error"))
                } catch (e:JSONException){
                    Log.d(TAG, "The error message: ${e}")
                }
            }
            Log.d(TAG, "SafeApiRequest: ${message}")
            throw Exception(message.toString())
        }
    }

}