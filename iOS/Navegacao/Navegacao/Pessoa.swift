//
//  Pessoa.swift
//  Navegacao
//
//  Created by IFPB on 07/11/2019.
//  Copyright © 2019 IFPB. All rights reserved.
//

import Foundation


class Pessoa: NSObject {
    var nome: String
    var idade: Int
    
    override var description: String{
        return "\(nome) - \(idade)"
    }
    
    init(nome:String, idade:Int){
        self.nome = nome
        self.idade = idade
    }
    
}
