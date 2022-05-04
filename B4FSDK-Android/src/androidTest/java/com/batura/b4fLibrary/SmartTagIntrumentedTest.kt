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
class SmartTagIntrumentedTest {

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
    fun getSmartTags() {

        var result : Either<B4FError?, SmartTagList?>?  = null

        exposeRest.smartTag.getSmartTags(0, 20){
            result = it
            signal.countDown()
        }

        signal.await()

        assertTrue(result!!.isRight)
    }

    @Test
    fun getSmartTagById() {

        var result : Either<B4FError?, SmartTagDetail?>?  = null

        exposeRest.smartTag.getSmartTagById("F97D2CE7E31BA86DDD235F75842B03B1"){
            result = it
            signal.countDown()
        }

        signal.await()

        assertTrue(result!!.isRight)
    }


    @Test
    fun disassociateSmartTagOfUser() {

        var result : Either<B4FError?, Unit?>?  = null

        exposeRest.smartTag.disassociateSmartTag("427A941285",
            ) {
            result = it
            signal.countDown()
        }

        signal.await()

        assertTrue(result!!.isLeft && result!!.getLeft()!!.b4FErrorData!!.key == "SmartTag_SmartTagDoesNotExist")
    }

    @Test
    fun associateSmartTagOfUser() {

        var result : Either<B4FError?, Unit?>?  = null

        exposeRest.smartTag.associateSmartTag("427A941285",
        ) {
            result = it
            signal.countDown()
        }

        signal.await()

        assertTrue(result!!.isLeft && result!!.getLeft()!!.b4FErrorData!!.key == "SmartTag_SmartTagAssociatedToOtherUser")
    }



}