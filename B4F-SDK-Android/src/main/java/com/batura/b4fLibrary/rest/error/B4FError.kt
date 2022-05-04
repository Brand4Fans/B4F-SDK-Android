package com.batura.b4fLibrary.rest.error

import com.batura.b4fLibrary.api.Failure
import com.squareup.moshi.Json

/**
 * This class involve 3 types of error HTTP error code, backend controlled error and other uncaughtErrors
 * @param httpCode the http error code the backend response
 * @param b4FErrorData if not null this field has the controlled error of the backend
 * @param uncaughtError when the error is not controlled this field has the message of any exception
 */
data class B4FError(@field:Json(name = "httpCode")val httpCode: Int?,
                    @field:Json(name = "b4fErrorData")val b4FErrorData: B4FErrorData?,
                    @field:Json(name = "uncaughtError")val uncaughtError : String?) : Failure.FeatureFailure()