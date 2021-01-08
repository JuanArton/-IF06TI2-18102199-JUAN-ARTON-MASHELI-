package com.JuanArtonM_18102199.Praktikum8

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.telephony.SmsMessage
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_sms_receiver.*

class SmsReceiver : BroadcastReceiver() {

    private val TAG = SmsReceiver::class.java.simpleName

    companion object{
        const val EXTRA_SMS_NO = "extra_sms_no"
        const val EXTRA_SMS_MESSAGE = "extra_sms_message"

        val banned_words = arrayListOf(
            "hadiah", "blogspot", "wordpress", "pulsa", "selamat",
            "transfer", "mobil", "polisi", "rumah"
        )
        var x: Int = 0
        var detect: Int = 0
        var colour: String = "#00dd33"
    }

    override fun onReceive(context: Context, intent: Intent) {
        val bundle = intent.extras
        try {
            if (bundle != null) {
                val pdusObj = bundle.get("pdus") as Array<Any>
                for (aPdusObj in pdusObj) {
                    val currentMessage = getIncomingMessage(aPdusObj, bundle)
                    val senderNum = currentMessage.displayOriginatingAddress.toString()
                    val message = currentMessage.displayMessageBody

                    for (i in 0..8){
                        val pattern = banned_words[i].toRegex()
                        val matches = pattern.findAll(message.toLowerCase())
                        matches.forEach { f ->
                            detect++
                        }
                        if(detect >1){
                            SmsReceiverActivity.status = "Hati-hati! SMS ini terindikasi penipuan"
                            colour = "#dc3545"
                        }
                        else{
                            SmsReceiverActivity.status = "Pesan ini aman"
                            colour = "#00dd33"
                        }
                    }

                    Log.d(TAG, "senderNum: $senderNum; message: $message")
                    val showSmsIntent = Intent(context, SmsReceiverActivity::class.java)
                    showSmsIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    showSmsIntent.putExtra(SmsReceiverActivity.EXTRA_SMS_NO, senderNum)
                    showSmsIntent.putExtra(SmsReceiverActivity.EXTRA_SMS_MESSAGE, message)
                    context.startActivity(showSmsIntent)
                }
            }
        } catch (e: Exception) {
            Log.d(TAG, "Exception smsReceiver$e")
        }
    }

    private fun getIncomingMessage(aObject: Any, bundle: Bundle): SmsMessage {
        val currentSMS: SmsMessage
        if (Build.VERSION.SDK_INT >= 23) {
            val format = bundle.getString("format")
            currentSMS = SmsMessage.createFromPdu(aObject as ByteArray, format)
        } else currentSMS = SmsMessage.createFromPdu(aObject as ByteArray)
        return currentSMS
    }
}