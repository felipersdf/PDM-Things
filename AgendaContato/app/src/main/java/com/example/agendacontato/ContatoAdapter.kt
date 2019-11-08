package com.example.agendacontato

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.contato_layout.view.*
import java.io.File

class ContatoAdapter(var context: Context, var listaContatos: ArrayList<Contato>) : RecyclerView.Adapter<ContatoAdapter.ContatoViewHolder>(){

//    lateinit var listaContatos: ArrayList<Contato>

    class ContatoViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        val foto = itemView.ivContatoFoto
        val nome = itemView.tvContatoNome
        val email = itemView.tvContatoEmail
        val fone = itemView.tvContatoFone

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContatoViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.contato_layout, parent, false)
        return ContatoViewHolder(v)
    }

    override fun getItemCount(): Int {
        return this.listaContatos.count()
    }

    override fun onBindViewHolder(holder: ContatoViewHolder, position: Int) {
        val contato = this.listaContatos.get(position)

        holder.nome.text = contato.nome
        holder.email.text = contato.email
        holder.fone.text = contato.fone

//        val imagemFoto = contato.foto as File
//        if (imagemFoto.exists()) {
//            val bitmap = BitmapFactory.decodeFile(imagemFoto.absolutePath)
//            holder.foto.setImageBitmap(bitmap)
//        }

    }

    fun update(){
        notifyDataSetChanged()
    }
}