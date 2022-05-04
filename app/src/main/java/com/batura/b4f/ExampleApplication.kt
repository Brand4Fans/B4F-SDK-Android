package com.batura.b4f

import android.app.Application
import com.batura.b4fLibrary.rest.B4F

class ExampleApplication : Application() {

    private val androidID = "7134743777217A25"

    override fun onCreate() {
        super.onCreate()
        B4F.init(this, androidID)
    }
}