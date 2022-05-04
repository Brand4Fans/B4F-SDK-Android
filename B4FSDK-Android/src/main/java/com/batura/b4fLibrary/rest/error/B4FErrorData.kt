package com.batura.b4fLibrary.rest.error

import com.squareup.moshi.Json

/**
 * @param key the key of the error, is unique
 * @param message a description of the error
 * @param code httpError statusCode
 */
data class B4FErrorData(@field:Json(name = "Key")val key : String,
                        @field:Json(name = "Message")val message: String?,
                        @field:Json(name = "StatusCode") val code: Int?){

}