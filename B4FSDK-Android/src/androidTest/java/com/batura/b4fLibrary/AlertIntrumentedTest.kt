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
class AlertIntrumentedTest {

    lateinit var exposeRest : B4F


    val androidID = "7134743777217A25"

    var signal: CountDownLatch = CountDownLatch(1)

    @Before
    fun setup(){
        B4F.init(InstrumentationRegistry.getInstrumentation().targetContext, androidID)
        exposeRest = B4F.single()
        exposeRest.auth.setUserData("BaturaiOS","ghjg")
        signal = CountDownLatch(1)
    }

    @Test
    fun getAlerts() {

        var result : Either<B4FError?, AlertList?>?  = null

        exposeRest.alerts.getAlerts(0, 20){
            result = it
            signal.countDown()
        }

        signal.await()

        assertTrue(result!!.isRight)
    }

    @Test
    fun getNumberAlertsCount() {

        var result : Either<B4FError?, AlertCountNotRead?>?  = null

        exposeRest.alerts.getNotReadAlertCount {
            result = it
            signal.countDown()
        }

        signal.await()

        assertTrue(result!!.isRight)
    }

    @Test
    fun setAlertRead() {

        var result : Either<B4FError?, Unit?>?  = null

        exposeRest.alerts.setAlertReadById("82FAB5768BE2695F2A3C025D83B89F47"){
            result = it
            signal.countDown()
        }

        signal.await()

        assertTrue(result!!.isRight)
    }

    @Test
    fun setAllAlertsRead() {

        var result : Either<B4FError?, Unit?>?  = null

        exposeRest.alerts.setAllAlertsRead {
            result = it
            signal.countDown()
        }

        signal.await()

        assertTrue(result!!.isRight)
    }

}