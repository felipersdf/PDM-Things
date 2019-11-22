//
//  Cadastro.swift
//  Papai Noel
//
//  Created by IFPB on 22/11/2019.
//  Copyright © 2019 IFPB. All rights reserved.
//

import Foundation

class Cadastro: NSObject {
    var lista: Array<Presente>

    override init() {
        self.lista = Array<Presente>()
    }
    
    func add(presente: Presente) {
        self.lista.append(presente)
    }
    
    func get() -> Array<Presente> {
        return self.lista
    }
    
    func get(index: Int) -> Presente {
        return self.lista[index]
    }
    
    func count() -> Int{
        return self.lista.count
    }
    
    func del(index: Int){
        self.lista.remove(at: index)
    }
 }
