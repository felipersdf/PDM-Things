package com.example.agendacontato

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.widget.AdapterView
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            val it = Intent(this, EditActivity::class.java)
            val teste = Contato("Felipe", "felipe@email.com", "930172392")
            it.putExtra("CONTATO", teste)
            startActivityForResult(it, ADD_CONTATO)
        }

        this.dao = ContatoDAO(this)
        this.lista = dao.getListContatos("ASC")

        this.contatoRecycler = findViewById(R.id.contatosRecy)
        this.contatoRecycler.setHasFixedSize(true)
        this.contatoRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL ,false)

        this.contatoRecycler.adapter = ContatoAdapter(this, this.lista)


    }
/////// Criando Listener para o Recycler View
    lateinit var mClickListener: ClickListener

    fun setOnItemClickListener(aClickListener: ClickListener) {
        mClickListener = aClickListener
    }

    interface ClickListener {
        fun onClick(pos: Int, aView: View)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        override fun onClick(v: View) {
            mClickListener.onClick(adapterPosition, v)
        }
    }

/// --- Fim do Listener

    fun atualizar(){
        this.lista.clear()
        this.lista.addAll(this.dao.getListContatos("ASC"))
        (this.contatoRecycler.adapter as ContatoAdapter).update()
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

            if(resultCode == RESULT_OK){
                if(requestCode == ADD_CONTATO){
                //Add contato
                    val c = data?.getSerializableExtra("CONTATO") as Contato
                    this.dao.inserirContato(c)

                    this.lista = dao.getListContatos("ASC")
                    this.atualizar()
                }
            }else if (requestCode == EDIT_CONTATO){
                //Editar contato
                val c = data?.getSerializableExtra("CONTATO") as Contato
                this.dao.updateContato(c.id, c)

                this.lista = dao.getListContatos("ASC")
                this.atualizar()
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
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
