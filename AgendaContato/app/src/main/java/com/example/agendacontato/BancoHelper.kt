package com.example.agendacontato

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BancoHelper(context: Context?) : SQLiteOpenHelper (context, "contatos.db", null, 1){
    override fun onCreate(db: SQLiteDatabase?) {
        val sql = "create table if not exists contatos(" +
                        "id integer primary key autoincrement," +
                        "nome text not null," +
                        "email text," +
                        "fone text," +
                        "foto text" +
                ");"
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }
}