package com.example.banco

import android.content.ContentValues
import android.content.Context

class PessoaDAO {
    var bancoHelper: BancoHelper

    constructor(context: Context){
        this.bancoHelper = BancoHelper(context)
    }

    fun insert(pessoa: Pessoa){
        val banco = this.bancoHelper.writableDatabase
        val cv = ContentValues()
        cv.put("nome", pessoa.nome)
        cv.put("idade", pessoa.idade)
        banco.insert("pessoas", null, cv)

    }

    fun get(id: Int): Pessoa?{
        return null
    }

    fun get():ArrayList<Pessoa>{
        val lista = arrayListOf<Pessoa>()
        val banco = this.bancoHelper.readableDatabase
        val colunas = arrayOf("id", "nome", "idade")

        val c = banco.query("pessoas", colunas, null, null, null, null, null)
        c.moveToFirst()

        do {
            val id = c.getInt(c.getColumnIndex("id"))
            val nome = c.getString(c.getColumnIndex("nome"))
            val idade = c.getInt(c.getColumnIndex("idade"))
            val pessoa = Pessoa(id, nome, idade)
            lista.add(pessoa)

        } while(c.moveToNext())

        return lista
    }

    fun update(id: Int, pessoa: Pessoa){
        val banco = bancoHelper.writableDatabase
        val cv = ContentValues()
        val where = "id = ?"
        val whereparams = arrayOf(id.toString())

        cv.put("nome", pessoa.nome)
        cv.put("idade", pessoa.idade)

        banco.update("pessoas", cv, where, whereparams)
    }

    fun delete(id: Int){
        val banco = bancoHelper.writableDatabase

        val where = "id = ?"
        val whereparams = arrayOf(id.toString())

        banco.delete("pessoas", where, whereparams)
    }
}