package com.batura.b4fLibrary


import com.batura.b4fLibrary.rest.ExposeRest
import com.batura.b4fLibrary.rest.error.B4FError
import com.batura.b4fLibrary.rest.models.BindRequest
import com.batura.b4fLibrary.rest.models.Bind
import org.awaitility.Awaitility
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import java.util.concurrent.TimeUnit

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    val exposeRest = ExposeRest()

    val androidID = "mrwNeBmv57cDoDJE/fNWyXiL9yuKeJDc6SkbhJVJGpw="

    @Before
    fun setup(){
    }

    @Test
    fun getAccessToken() {
        val dtoVinculationRequest = BindRequest("45dgfdsgdsdf",
            "3231","","Android")
        var test : Pair<Bind?, B4FError?>?  = null
        exposeRest.vinculation(dtoVinculationRequest){
            test = it
        }

        Awaitility.await().atMost(5, TimeUnit.SECONDS).until {
            test != null
        }

        assertTrue(test != null)
    }
}