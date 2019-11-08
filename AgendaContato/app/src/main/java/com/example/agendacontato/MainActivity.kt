package com.example.agendacontato

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var dao: ContatoDAO
    private lateinit var lista: ArrayList<Contato>
    private lateinit var contatoRecycler: RecyclerView


    val ADD_CONTATO = 1
    val EDIT_CONTATO = 2
    var order = "ASC"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            val it = Intent(this, EditActivity::class.java)
            val teste = Contato("", "", "")
            it.putExtra("CONTATO", teste)
            startActivityForResult(it, ADD_CONTATO)
        }

        this.dao = ContatoDAO(this)
        this.lista = dao.getListContatos(order)

        this.contatoRecycler = findViewById(R.id.contatosRecy)
        this.contatoRecycler.setHasFixedSize(true)
        this.contatoRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL ,false)

        this.contatoRecycler.adapter = ContatoAdapter(this, this.lista)

        this.contatoRecycler.addOnItemClickListener(object: OnItemClickListener{
            override fun onItemClicked(position: Int, view: View) {
                    abrirOpcoes(lista.get(position))
            }
        })
    }

    fun abrirOpcoes(contato: Contato){
        val builder = AlertDialog.Builder(this)
        val itens = arrayOf("Editar", "Enviar Email", "Excluir")

        builder.setTitle(contato.nome)
        builder.setItems(itens) {dialog, which ->
            if(itens[which] == "Editar"){
                val itt = Intent(this, EditActivity::class.java)
                itt.putExtra("CONTATO", contato)
                startActivityForResult(itt, EDIT_CONTATO)

            }else if(itens[which] == "Enviar Email"){
                val uri = Uri.parse("mailto:${contato.email}")
                val it = Intent(Intent.ACTION_SENDTO, uri)
                it.putExtra(Intent.EXTRA_SUBJECT, "Assunto")
                it.putExtra(Intent.EXTRA_TEXT, "Texto")
                startActivity(it)

            }else if(itens[which] == "Excluir"){
                lista.remove(contato)
                dao.deleteContato(contato.id)
                this.contatoRecycler.adapter?.notifyDataSetChanged()
            }

        }

        builder.create().show()

    }



    /// Adicionando Listener para o Recycler View

    interface OnItemClickListener {
        fun onItemClicked(position: Int, view: View)
    }

    fun RecyclerView.addOnItemClickListener(onClickListener: OnItemClickListener) {
        this.addOnChildAttachStateChangeListener(object : RecyclerView.OnChildAttachStateChangeListener {
            override fun onChildViewDetachedFromWindow(view: View) {
                view?.setOnClickListener(null)
            }

            override fun onChildViewAttachedToWindow(view: View) {
                view?.setOnClickListener({
                    val holder = getChildViewHolder(view)
                    onClickListener.onItemClicked(holder.adapterPosition, view)
                })
            }
        })
    }

    //// ---------------------------------------------------------


    fun atualizar(){
        this.lista.clear()
        this.lista.addAll(this.dao.getListContatos(order))
        this.contatoRecycler.adapter?.notifyDataSetChanged()
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

            if(resultCode == RESULT_OK){
                if(requestCode == ADD_CONTATO){
                //Add contato
                    Log.i("App_Contato", "PASSEI POR AQUI - ADD")
                    val c = data?.getSerializableExtra("CONTATO") as Contato
                    this.dao.inserirContato(c)

                    this.atualizar()
                    this.contatoRecycler.adapter?.notifyDataSetChanged()
                } else if (requestCode == EDIT_CONTATO){
                    //Editar contato
                    Log.i("App_Contato", "PASSEI POR AQUI")
                    val c = data?.getSerializableExtra("CONTATO") as Contato
                    this.dao.updateContato(c.id, c)

                    this.atualizar()
                    this.contatoRecycler.adapter?.notifyDataSetChanged()
            }

            }

    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.order_az -> {
                order = "ASC"
                this.lista = dao.getListContatos(order)
                this.contatoRecycler.adapter = ContatoAdapter(this, lista)
                true
            }
            R.id.order_za -> {
                order = "DESC"
                this.lista = dao.getListContatos(order)
                this.contatoRecycler.adapter = ContatoAdapter(this, lista)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
