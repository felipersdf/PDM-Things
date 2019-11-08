package com.example.servios

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.util.jar.Manifest
import android.os.Bundle as Bundle

class MainActivity : AppCompatActivity() {

    private lateinit var botaoHTML: Button
    private lateinit var botaoDISCAR: Button
    private lateinit var botaoLIGAR: Button
    private lateinit var botaoEMAIL: Button
    private lateinit var botaoSMS: Button
    private lateinit var botaoCOMPARTILHAR: Button
    private lateinit var botaoPONTO: Button
    private lateinit var botaoROTA: Button
    private lateinit var botaoYOUTUBE: Button
    private lateinit var botaoFOTO: Button
    val REQUEST_IMAGE_CAPTURE = 1
    private lateinit var imagem: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.botaoHTML = findViewById(R.id.btHTML)
        this.botaoDISCAR = findViewById(R.id.btDISCAR)
        this.botaoLIGAR = findViewById(R.id.btLIGAR)
        this.botaoEMAIL = findViewById(R.id.btEMAIL)
        this.botaoSMS = findViewById(R.id.btSMS)
        this.botaoCOMPARTILHAR = findViewById(R.id.btCOMPARTILHAR)
        this.botaoROTA = findViewById(R.id.btROTA)
        this.botaoPONTO = findViewById(R.id.btPONTO)
        this.botaoYOUTUBE = findViewById(R.id.btYOUTUBE)
        this.botaoFOTO = findViewById(R.id.btFOTO)
        this.imagem = findViewById(R.id.imgFOTO)

        this.botaoHTML.setOnClickListener({ onClickHtml(it) })
        this.botaoDISCAR.setOnClickListener( {onClickDiscar(it)} )
        this.botaoLIGAR.setOnClickListener( {onClickLigar(it)} )
        this.botaoEMAIL.setOnClickListener( {onClickEmail(it)} )
        this.botaoSMS.setOnClickListener( {onClickSms(it)} )
        this.botaoCOMPARTILHAR.setOnClickListener( {onClickCompartilhar(it)} )
        this.botaoROTA.setOnClickListener( {onClickRota(it)} )
        this.botaoPONTO.setOnClickListener( {onClickPonto(it)} )
        this.botaoYOUTUBE.setOnClickListener( {onClickYoutube(it)} )
        this.botaoFOTO.setOnClickListener( {onClickFoto(it)} )
    }

    fun onClickHtml(view: View){
        val uri = Uri.parse("http://www.google.com.br")
        val it = Intent(Intent.ACTION_VIEW, uri)
        startActivity(it)
    }

    fun onClickDiscar(view: View){
        val uri = Uri.parse("tel:332393")
        val it = Intent(Intent.ACTION_DIAL, uri)
        startActivity(it)
    }

    fun onClickLigar(view: View){
        val uri = Uri.parse("tel:332393")
        val it = Intent(Intent.ACTION_CALL, uri)
        val call = android.Manifest.permission.CALL_PHONE
        val granted = PackageManager.PERMISSION_GRANTED

        if(ContextCompat.checkSelfPermission(this, call) == granted){
            startActivity(it)
        }
    }

    fun onClickEmail(view: View){
        val uri = Uri.parse("mailto:felipersdf@gmail.com")
        val it = Intent(Intent.ACTION_SENDTO, uri)
        it.putExtra(Intent.EXTRA_SUBJECT, "Assunto")
        it.putExtra(Intent.EXTRA_TEXT, "Texto")
        startActivity(it)
    }

    fun onClickSms(view: View){
        val uri = Uri.parse("tel:332393")
        val it = Intent(Intent.ACTION_SENDTO, uri)
        it.putExtra("sms_body", "Mensagem")
        startActivity(it)
    }

    fun onClickCompartilhar(view: View){
        val it = Intent(Intent.ACTION_SEND)

        it.setType("text/plain")
        it.putExtra(Intent.EXTRA_TEXT, "Texto para compartilhar!!")
//        it.setPackage("com.whatsapp")

        startActivity(Intent.createChooser(it, "Compartilhar!!"))
    }

    fun onClickPonto(view: View){
        val uri = Uri.parse("geo:-3.320131,-34.143912")
        val it = Intent(Intent.ACTION_VIEW, uri)
        startActivity(it)
    }

    fun onClickRota(view: View){
        val origem = "-7.1356496,-34.8760932"
        val destino = "-7.1181836,-34.8730402"
        val url = "http://maps.google.com/maps"

        val uri = Uri.parse("${url}?f=&saddr=${origem}+&daddr=${destino}")
        val it = Intent(Intent.ACTION_VIEW, uri)
        startActivity(it)
    }

    fun onClickYoutube(view: View){
        val uri = Uri.parse("vnd.youtube://H0Z7ewOXCKw")
        val it = Intent(Intent.ACTION_VIEW, uri)
        startActivity(it)
    }

    fun onClickFoto(view: View){
        val it = Intent(MediaStore.ACTION_IMAGE_CAPTURE).also {
            takePictureIntent -> takePictureIntent.resolveActivity(packageManager)?.also{
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
             }
        }
    }

     override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

         if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
             val imageBitmap = data?.extras?.get("data") as Bitmap
             this.imagem.setImageBitmap(imageBitmap)
         }
    }
}
