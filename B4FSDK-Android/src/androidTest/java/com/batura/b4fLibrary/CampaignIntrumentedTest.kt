package com.batura.b4fLibrary

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.batura.b4fLibrary.rest.B4F
import com.batura.b4fLibrary.rest.error.B4FError
import com.batura.b4fLibrary.rest.models.CampaignDetail
import com.batura.b4fLibrary.rest.models.CampaignFilter
import com.batura.b4fLibrary.rest.models.CampaignList
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
class CampaignIntrumentedTest {

    lateinit var exposeRest : B4F
    private val androidID = "7134743777217A25"

    var signal: CountDownLatch = CountDownLatch(1)

    @Before
    fun setup(){
        B4F.init(InstrumentationRegistry.getInstrumentation().targetContext, androidID)
        exposeRest = B4F.single()

        exposeRest.auth.setUserData("BaturaiOS","ghjg")
        signal = CountDownLatch(1)
    }
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.batura.b4flibrary.test", appContext.packageName)
        exposeRest.auth.setUserData("caO7Zi0h57UzT771m0VZRcD6d1Y2")
    }

    @Test
    fun getMyCampaign() {

        var result : Either<B4FError?, CampaignList?>?  = null

        exposeRest.campaign.getMyCampaigns(0, 20){
            result = it
            signal.countDown()
        }

        signal.await()

        assertTrue(result!!.isRight)
    }

    @Test
    fun getCampaignAvailable() {

        var result : Either<B4FError?, CampaignList?>?  = null

        exposeRest.campaign.getAvailableCampaigns(0, 20){
            result = it
            signal.countDown()
        }

        signal.await()

        assertTrue(result!!.isRight)
    }

    @Test
    fun getCampaignFilter() {

        var result : Either<B4FError?, CampaignFilter?>?  = null

        exposeRest.campaign.getFilterCampaigns {
            result = it
            signal.countDown()
        }

        signal.await()

        assertTrue(result!!.isRight)
    }

    @Test
    fun getCampaignWithId() {

        var result : Either<B4FError?, CampaignDetail?>?  = null

        exposeRest.campaign.getCampaignById("FF1A28C48DB9DD51AC44B9627BA7BB55") {
            result = it
            signal.countDown()
        }

        signal.await()

        assertTrue(result!!.isRight)
    }

    @Test
    fun subscribeInCampaign() {

        var result : Either<B4FError?, Unit?>?  = null

        exposeRest.campaign.subscribeToCampaignWithId("FF1A28C48DB9DD51AC44B9627BA7BB55","dffsf",
            ) {
            result = it
            signal.countDown()
        }

        signal.await()

        assertTrue(result!!.isLeft && result!!.getLeft()!!.b4FErrorData!!.key == "BBDD_Error_200")
    }

    @Test
    fun linkAndSubscribeInCampaign() {

        var result : Either<B4FError?, Unit?>?  = null

        exposeRest.campaign.linkAndSubscribeToCampaignWithId("FF1A28C48DB9DD51AC44B9627BA7BB55","dffsf",
        ) {
            result = it
            signal.countDown()
        }

        signal.await()

        assertTrue(result!!.isLeft && result!!.getLeft()!!.b4FErrorData!!.key == "Campaign_SmartTagDoesNotExist")
    }



}