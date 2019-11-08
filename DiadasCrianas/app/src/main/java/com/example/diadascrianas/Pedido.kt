package com.example.diadascrianas

import java.io.Serializable

class Pedido (var nome:String, var presente:String) : Serializable {
    override fun toString(): String {
        return "Pedido: ${nome} - ${presente}"
    }
}