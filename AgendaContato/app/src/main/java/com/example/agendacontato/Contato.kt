package com.example.agendacontato

import java.io.Serializable

class Contato: Serializable{

    var id: Int
    var nome: String
    var email: String
    var fone: String
    var foto:String

    constructor(nome:String, email:String, fone:String, foto:String){
        this.id = -1
        this.nome = nome
        this.email = email
        this.fone = fone
        this.foto = foto
    }

    constructor(id: Int, nome:String, email:String, fone:String, foto:String){
        this.id = id
        this.nome = nome
        this.email = email
        this.fone = fone
        this.foto = foto
    }

    constructor(nome:String, email:String, fone:String){
        this.id = -1
        this.nome = nome
        this.email = email
        this.fone = fone
        this.foto = ""
    }
}