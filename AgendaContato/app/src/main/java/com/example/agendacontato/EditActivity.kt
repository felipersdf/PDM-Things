package com.example.agendacontato

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaScannerConnection
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.material.snackbar.Snackbar
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*

class EditActivity : AppCompatActivity() {

    private lateinit var layout: View

    private lateinit var contato: Contato

    private lateinit var ibfoto: ImageButton
    private lateinit var etNome: EditText
    private lateinit var etEmail: EditText
    private lateinit var etFone: EditText
    private lateinit var btSalvar: Button
    private lateinit var btVoltar: Button

    val CAMERA = 1
    val GALERIA = 2

    val IMAGE_DIR = "/FotosContatos"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        contato = getIntent().getSerializableExtra("CONTATO") as Contato

        this.layout = findViewById(R.id.clEditMainLayout)
        this.ibfoto = findViewById(R.id.ibEditfotoContato)
        this.etNome = findViewById(R.id.etEditNomeContato)
        this.etEmail = findViewById(R.id.etEditEmailContato)
        this.etFone = findViewById(R.id.etEditFoneContato)
        this.btSalvar = findViewById(R.id.btEditSalvar)
        this.btVoltar = findViewById(R.id.btEditVoltar)

        //Ediçao
//        etNome.setText(contato.nome)
//        etEmail.setText(contato.email)
//        etFone.setText(contato.fone)

        this.ibfoto.setOnClickListener(View.OnClickListener {
            alertaImagem(it)
        })

//        //Carrega foto que ja possui no banco
//        val imagemFoto = contato.foto as File
//        if(imagemFoto.exists()){
//            val bitmap = BitmapFactory.decodeFile(imagemFoto.absolutePath)
//            ibfoto.setImageBitmap(bitmap)
//        }


        this.btSalvar.setOnClickListener({ clickSalvar(it) })
        this.btVoltar.setOnClickListener({
            finish()
        })
    }

    fun clickSalvar(view: View) {
        contato.nome = this.etNome.text.toString()
        contato.email = this.etEmail.text.toString()
        contato.fone = this.etFone.text.toString()
        // a fazer
        val foto = ""

        if (contato.nome == "") {
            Toast.makeText(this, "Adicione um nome ao contato!", Toast.LENGTH_SHORT).show()
            return
        }
//        val contato = Contato(nome, email, telefone, foto)
        val itt = Intent()
        itt.putExtra("CONTATO", contato)
        setResult(Activity.RESULT_OK, itt)
        finish()
    }


    fun alertaImagem(view: View) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Selecione a fonte da imagem")
        builder.setPositiveButton("Camera") { dialog, which ->
            tirarFoto()
        }
        builder.setNegativeButton("Galeria") { dialog, which ->
            carregaImagem()
        }

        builder.create().show()
    }

    fun carregaImagem() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            pedirPermissaoGaleria()
        } else {
            abrirGaleria()
        }
    }

    fun pedirPermissaoGaleria() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
        ) {
            val snack = Snackbar.make(
                layout,
                "E necessario permissao para utilizar a galeria!",
                Snackbar.LENGTH_INDEFINITE
            )

            snack.setAction("OK", View.OnClickListener {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ),
                    GALERIA
                )
            })
            snack.show()

        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ),
                GALERIA
            )
        }
    }

    fun abrirGaleria() {
        val itt = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(itt, GALERIA)
    }

    fun tirarFoto() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            pedirPermissaoCamera()
        } else {
            abrirCamera()
        }
    }

    fun pedirPermissaoCamera() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.CAMERA
            )
        ) {
            val snack = Snackbar.make(
                layout,
                "E necessario permitir para utilizar a camera!",
                Snackbar.LENGTH_INDEFINITE
            )

            snack.setAction("OK", View.OnClickListener {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    CAMERA
                )
            })
            snack.show()

        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE),
                CAMERA
            )
        }
    }

    fun abrirCamera() {
        val itt = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(itt, CAMERA)

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            CAMERA -> if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                tirarFoto()
            }
            GALERIA -> if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                carregaImagem()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == Activity.RESULT_CANCELED || data == null) {
            return
        }
        if (requestCode == GALERIA) {
            val uri = data.data as Uri
            val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, uri)

            this.contato.foto = (salvarImagem(bitmap))
            this.ibfoto!!.setImageBitmap(bitmap)


        } else if (requestCode == CAMERA) {
            val bitmap = data.extras?.get("data") as Bitmap
            this.contato.foto = salvarImagem(bitmap)
            this.ibfoto.setImageBitmap(bitmap)
        }
    }

    @Throws(IOException::class)
    fun salvarImagem(bitmap: Bitmap): String {
        val bytes = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bytes)

        //Diretorio da Imagem -------
//        val directory = File(Environment.getExternalStorageState() + IMAGE_DIR)
        val directory = getExternalFilesDir(Environment.DIRECTORY_PICTURES) as File

        if (!directory.exists()) {
            directory.mkdirs()
        }
        /// ---------------

        // Criaçao da imagem
//        val arquivoImagem = File(directory, Calendar.getInstance().timeInMillis.toString() + ".jpg")
//            arquivoImagem.createNewFile()
        val arquivoImagem = File.createTempFile(Calendar.getInstance().timeInMillis.toString(), ".jpg", directory)
        val fo = FileOutputStream(arquivoImagem)
            fo.write(bytes.toByteArray())

            MediaScannerConnection.scanFile(this, arrayOf(arquivoImagem.path), arrayOf("image/jpeg"), null)
            fo.close()

        return arquivoImagem.absolutePath
    }
}