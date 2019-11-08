package com.example.agendacontato

import android.content.ContentValues
import android.content.Context

class ContatoDAO {
    var bancoHelper: BancoHelper

    constructor(context: Context){
        this.bancoHelper = BancoHelper(context)
    }

    fun getListContatos(ordem: String): ArrayList<Contato>{
        val contatos = arrayListOf<Contato>()
        val banco = this.bancoHelper.readableDatabase
        val colunas = arrayOf("id", "nome", "email", "fone", "foto")

        val cursor = banco.query("contatos", colunas, null, null, null, null, null)
//        val cursor = banco.rawQuery("SELECT * FROM contatos ORDER BY nome " + ordem + ";", null)

        if(cursor != null && cursor.count > 0){
            cursor.moveToFirst()

        do{
            val id = cursor.getInt(cursor.getColumnIndex("id"))
            val nome = cursor.getString(cursor.getColumnIndex("nome"))
            val email = cursor.getString(cursor.getColumnIndex("email"))
            val fone = cursor.getString(cursor.getColumnIndex("fone"))
            val foto = cursor.getString(cursor.getColumnIndex("foto"))

            val contato = Contato(id, nome, email, fone, foto)
            contatos.add(contato)
        }while(cursor.moveToNext())

        }

        return contatos
    }

    fun inserirContato(contato: Contato){
        val banco= this.bancoHelper.writableDatabase
        val cv = ContentValues()

        cv.put("nome", contato.nome)
        cv.put("email", contato.email)
        cv.put("fone", contato.fone)
        cv.put("foto", contato.foto)

        banco.insert("contatos", null, cv)

    }

    fun updateContato(id:Int, contato: Contato){

        val banco= this.bancoHelper.writableDatabase
        val cv = ContentValues()
        val where = "id = ?"
        val whereparams = arrayOf(id.toString())

        cv.put("nome", contato.nome)
        cv.put("email", contato.email)
        cv.put("fone", contato.fone)
        cv.put("foto", contato.foto)

        banco.update("contatos", cv, where, whereparams)
    }

    fun deleteContato(id: Int){
        val banco = bancoHelper.writableDatabase

        val where = "id = ?"
        val whereparams = arrayOf(id.toString())

        banco.delete("contatos", where, whereparams)
    }
}