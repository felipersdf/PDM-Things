package com.example.appintentservice

import android.app.IntentService
import android.content.Intent
import android.util.Log

class IntentService : IntentService("IntentService") {
    override fun onHandleIntent(intent: Intent?) {
        for (i in 1..10){
            Log.d("IntentService", intent?.getIntExtra("tipo",0).toString()+" " +i)
            Thread.sleep(1000)
        }
    }

}