package com.example.cnb

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class AviaoReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val status = intent.getBooleanExtra("state", false)
        Toast.makeText(context, "Modo aviao foi alterado! - ${status}", Toast.LENGTH_SHORT).show()
    }
}
