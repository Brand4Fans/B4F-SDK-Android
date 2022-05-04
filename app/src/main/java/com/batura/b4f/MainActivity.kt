package com.batura.b4f

import android.nfc.tech.*
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.batura.b4fLibrary.nfc.B4FDelegate


class MainActivity : AppCompatActivity() {

     private val B4FDelegate = B4FDelegate()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        B4FDelegate.onCreate(this){
            //In this function function you will receive the result of the RFID TAG
        }

        //Change to true when you wan't receive
        B4FDelegate.changeStatusReadable(true)
    }

    override fun onPause() {
        super.onPause()
        B4FDelegate.onPause()
    }

    override fun onResume() {
        super.onResume()
        B4FDelegate.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        B4FDelegate.onDestroy()
    }

}