package com.example.appintentservice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    private val tipo1= 1
    private val tipo2= 2


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(this,IntentService::class.java)
        intent.putExtra("tipo",tipo1)
        startService(intent)
        val intent2 = Intent(this,IntentService::class.java)
        intent2.putExtra("tipo",tipo2)
        startService(intent2)

    }
}
