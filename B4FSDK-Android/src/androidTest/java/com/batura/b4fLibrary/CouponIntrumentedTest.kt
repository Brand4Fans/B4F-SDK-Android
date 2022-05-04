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
class CouponIntrumentedTest {

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
    fun getCouponToUse() {

        var result : Either<B4FError?, CouponListItem?>?  = null

        exposeRest.coupon.getCoupons(0, 20){
            result = it
            signal.countDown()
        }

        signal.await()

        assertTrue(result!!.isRight)
    }

    @Test
    fun getCouponsUnavaiable() {

        var result : Either<B4FError?, CouponListItem?>?  = null

        exposeRest.coupon.getUnavailableCoupons(0, 20){
            result = it
            signal.countDown()
        }

        signal.await()

        assertTrue(result!!.isRight)
    }

    @Test
    fun getCouponsFilters() {

        var result : Either<B4FError?, CouponFilter?>?  = null

        exposeRest.coupon.getFiltersCoupon {
            result = it
            signal.countDown()
        }

        signal.await()

        assertTrue(result!!.isRight)
    }

    @Test
    fun getCouponWithId() {

        var result : Either<B4FError?, CouponDetail?>?  = null

        exposeRest.coupon.getCouponByID("39B774091364043B02F36F293DADD46C") {
            result = it
            signal.countDown()
        }

        signal.await()

        assertTrue(result!!.isRight)
    }

    @Test
    fun setCouponAsRedeemByID() {

        var result : Either<B4FError?, Unit?>?  = null

        exposeRest.coupon.redeemCouponWithId("EF4CB72E4ADAAD7F6066F5E1302C0DA7",
            ) {
            result = it
            signal.countDown()
        }

        signal.await()

        assertTrue(result!!.isLeft && result!!.getLeft()!!.b4FErrorData!!.key == "Campaign_CampaignMustBeCouponTypeToRedeem")
    }

    @Test
    fun unsubscribeCouponByID() {

        var result : Either<B4FError?, Unit?>?  = null

        exposeRest.coupon.unsubscribeFromCampaignWithCouponId("EF4CB72E4ADAAD7F6066F5E1302C0DA7",
        ) {
            result = it
            signal.countDown()
        }

        signal.await()

        assertTrue(result!!.isLeft && result!!.getLeft()!!.b4FErrorData!!.key == "Campaign_SmartTagNotAssociated")
    }



}