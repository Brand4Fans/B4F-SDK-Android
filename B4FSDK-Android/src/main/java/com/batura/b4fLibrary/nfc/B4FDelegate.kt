package com.batura.b4fLibrary.nfc

import android.app.Activity
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.nfc.NfcAdapter
import android.nfc.NfcManager
import android.nfc.tech.Ndef
import android.provider.Settings
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.nio.charset.Charset

class B4FDelegate() {

    private val READER_FLAGS = NfcAdapter.FLAG_READER_NFC_A or
            NfcAdapter.FLAG_READER_NO_PLATFORM_SOUNDS

    private var appContext : Context? = null

    private var nfcAdapter : NfcAdapter? = null

    private lateinit var pendingIntent : PendingIntent
    private var activity : Activity? = null

    private var nfcCallback :  ((nfcTagString: String) -> Unit)? = null

    /***
     * This function is need to be call in the onCreate of the activity
     * @param activity The activity is needed to work with nfc
     * @return false if the nfc has not been active
     *
     */
    fun onCreate(activity: Activity, nfcCallback: (nfcTagString: String) -> Unit) : Boolean{

        appContext = activity.applicationContext

        nfcAdapter = NfcAdapter.getDefaultAdapter(appContext)

        if (hasNFCActive()) {
            return false
        }
        val intent = Intent(appContext, activity::class.java)

        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        pendingIntent = PendingIntent.getActivity(appContext, 0, intent, 0)

        this.activity = activity

        this.nfcCallback = nfcCallback

        return true
    }
    private var isNeedNfcActivated = true
    private var hasResumedPassed = false

    /**
     * Change the status of readable nfc status.
     * This function only change the internal logical of read nfc
     * whereby if the nfc of the system is not activated you need activated separately
     * @param isNeedActivated enable of disable the delegate status of read nfc
     */
    fun changeStatusReadable(isNeedActivated: Boolean){
        isNeedNfcActivated = isNeedActivated
        if (hasResumedPassed){
            if (isNeedActivated){
                onResume()
            }else{
               onPause()
            }
        }
    }

    /***
     * This function is need to be call in the onResume of the activity
     */
    fun onResume(){
        hasResumedPassed = true
        if (isNeedNfcActivated){
            activity?.let {
                nfcAdapter?.enableReaderMode(it, { tag ->
                    if (tag.techList.contains("android.nfc.tech.Ndef")){
                        val nDef = Ndef.get(tag)
                        nDef.connect()
                        val offset = nDef.ndefMessage
                            .toByteArray()
                            .toString(Charset.forName("UTF-8"))
                        nDef.close()

                        nfcCallback?.let {  callback ->
                            GlobalScope.launch(Dispatchers.Main) {
                                callback(offset.substring(5))
                            }
                        }

                    }

                }, READER_FLAGS, null)
            }

        }
    }
    /***
     * This function is need to be call in the onPause of the activity
     */
    fun onPause(){
        hasResumedPassed = false
        if (isNeedNfcActivated){
            activity?.let {
                nfcAdapter?.disableReaderMode(it)
            }
        }
    }

    /***
     * This function is need to be call in the onDestroy of the activity
     */
    fun onDestroy(){
        activity = null
    }


    /**
     * Check if the device has nfc
     * @return true if the device has nfc
     */
    fun hasNfc(): Boolean {
        return activity?.getDefaultNfcAdapter()!= null
    }

    /**
     * Check if the device has nfc activated
     * @return true if the dice has nfc activated
     */
    fun hasNFCActive(): Boolean {
        return activity?.getDefaultNfcAdapter()!= null && activity!!.getDefaultNfcAdapter()!!.isEnabled
    }

    /**
     * Show the nfc request dialog
     */
    fun requestNFC(){
        activity?.startNfcSettingsActivity()
    }
}

/**
 * Get the default NFC adapter
 * @return null if no adapter has been found
 */
fun Activity.getDefaultNfcAdapter(): NfcAdapter? {
    val manager = getSystemService(Context.NFC_SERVICE) as NfcManager
    return manager.defaultAdapter
}

/**
 * Open nfc settings activity
 */
fun Activity.startNfcSettingsActivity() {
    startActivity(Intent(Settings.ACTION_NFC_SETTINGS))
}
