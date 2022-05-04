package com.batura.b4fLibrary

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.batura.b4fLibrary.rest.B4F
import com.batura.b4fLibrary.rest.error.B4FError
import com.batura.b4fLibrary.rest.models.*
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.CountDownLatch

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class UserInstrumentedTest {

    lateinit var exposeRest : B4F


    val androidID = "7134743777217A25"

    var signal: CountDownLatch = CountDownLatch(1)

    @Before
    fun setup(){
        B4F.init(InstrumentationRegistry.getInstrumentation().targetContext, androidID)
        exposeRest = B4F.single()

        exposeRest.auth.setUserData("caO7Zi0h57UzT771m0VZRcD6d1Y3","ghjg")
        signal = CountDownLatch(1)
    }

    @Test
    fun getUser() {

        var result : Either<B4FError?, User?>?  = null

        exposeRest.userProfile.getUser {
            result = it
            signal.countDown()
        }

        signal.await()

        assertTrue(result!!.isRight)
    }

    @Test
    fun updateUser() {

        var result : Either<B4FError?, Unit?>?  = null

        exposeRest.userProfile.updateUser(UpdateRequest("pepito","Juan","Raven")){
            result = it
            signal.countDown()
        }

        signal.await()

        assertTrue(result!!.isRight)
    }

    @Test
    fun updateToken() {

        var result : Either<B4FError?, Bind?>?  = null

        exposeRest.auth.refreshPushToken("newToken"){
            result  = it
            signal.countDown()
        }

        signal.await()

        assertTrue(result!!.isRight)
    }


    /*@Test
    fun deleteUser() {

        var result : Either<B4FError?, Unit?>?  = null

        exposeRest.userProfile.deleteUserProfile(
            ) {
            result = it
            signal.countDown()
        }

        signal.await()

        assertTrue(result!!.isRight)
    }*/



}