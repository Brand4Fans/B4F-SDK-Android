package com.batura.b4fLibrary.api

import com.batura.b4fLibrary.rest.error.B4FError
import com.batura.b4fLibrary.rest.error.B4FErrorData
import com.squareup.moshi.Moshi
import com.batura.b4fLibrary.Either

import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type

/**
 * @suppress
 */
internal class EitherCall<R>(
    private val delegate: Call<R>,
    private val successType: Type
) : Call<Either<B4FError, R>> {

    override fun enqueue(callback: Callback<Either<B4FError, R>>) = delegate.enqueue(
        object : Callback<R> {
            private val moshiErrorAdapter = Moshi.Builder().build().adapter(B4FErrorData::class.java)

            override fun onResponse(call: Call<R>, response: Response<R>) {
                callback.onResponse(this@EitherCall, Response.success(response.toEither()))
            }

            private fun Response<R>.toEither(): Either<B4FError, R> {
                // Http error response (4xx - 5xx)
                if (!isSuccessful) {
                    val errorBody = errorBody()?.string() ?: ""

                    val error = run {
                        try {
                            val errorParsed = moshiErrorAdapter.fromJson(errorBody)
                            if (errorParsed != null) {
                                B4FError(code(),errorParsed,null)
                            } else {
                                B4FError(code(), null, errorBody)
                            }
                        }catch (_:Throwable){
                            B4FError(code(),null,errorBody)
                        }
                    }

                    return Either.Left(error)
                }

                // Http success response with body
                body()?.let { body -> return Either.Right(body) }

                // if we defined Unit as success type it means we expected no response body
                // e.g. in case of 204 No Content
                return if (successType == Unit::class.java) {
                    @Suppress("UNCHECKED_CAST")
                    Either.Right(Unit) as Either<B4FError, R>
                } else {
                    @Suppress("UNCHECKED_CAST")
                    Either.Left(UnknownError("Response body was null")) as Either<B4FError, R>
                }
            }

            override fun onFailure(call: Call<R>, throwable: Throwable) {

                val error = when (throwable) {
                    else -> B4FError(null,null, throwable.message)
                }
                callback.onResponse(this@EitherCall, Response.success(Either.Left(error)))
            }
        }
    )

    override fun clone(): Call<Either<B4FError, R>> {
        return EitherCall(delegate.clone(), successType)
    }

    override fun execute(): Response<Either<B4FError, R>> {
       throw UnsupportedOperationException()
    }

    override fun isExecuted(): Boolean {
        return delegate.isExecuted
    }

    /**
     * @suppress
     */
    override fun cancel() {
       delegate.cancel()
    }

    override fun isCanceled(): Boolean {
        return delegate.isCanceled
    }

    override fun request(): Request {
        return delegate.request()
    }

    override fun timeout(): Timeout {
        return delegate.timeout()
    }


// override remaining methods - trivial
}


internal class EitherCallAdapter<R>(
    private val successType: Type
) : CallAdapter<R, Call<Either<B4FError, R>>> {

    override fun adapt(call: Call<R>): Call<Either<B4FError, R>> = EitherCall(call, successType)

    override fun responseType(): Type = successType
}