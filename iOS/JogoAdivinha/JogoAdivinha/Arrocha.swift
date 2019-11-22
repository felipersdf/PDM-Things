//
//  Arrocha.swift
//  JogoAdivinha
//
//  Created by IFPB on 14/11/2019.
//  Copyright © 2019 IFPB. All rights reserved.
//

import Foundation

class Arrocha: NSObject{
    var menor: Int
    var maior: Int
    var segredo: Int
    var status_finalizado: Bool
    var resultado: Bool
    
    override init() {
        self.menor = 1
        self.maior = 100
        self.segredo = Int.random(in: self.menor + 1 ... self.maior - 1)
        self.status_finalizado = false
        self.resultado = false
    }
    
    func chute(valor: Int) {
        if(self.status_finalizado == false){
            if (valor == self.segredo) {
                self.status_finalizado = true
                self.resultado = true
            }else if (valor == self.menor || valor == self.maior){
                self.status_finalizado = true
            }else {
                if(valor < self.segredo) {
                    self.menor = valor
                }else{
                    self.maior = valor
                }
                if (self.menor+1 == self.maior-1){
                    self.status_finalizado = true
                }
            }
        }
    }
}


