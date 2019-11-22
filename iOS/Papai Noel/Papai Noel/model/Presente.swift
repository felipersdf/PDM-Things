//
//  Presente.swift
//  Papai Noel
//
//  Created by IFPB on 22/11/2019.
//  Copyright © 2019 IFPB. All rights reserved.
//

import Foundation

class Presente: NSObject {
    var descricao: String
    var grau: Int
    
    override var description: String{
        return "\(descricao) - \(grau)"
    }
    
    init(descricao: String, grau: Int){
        self.descricao = descricao
        self.grau = grau
    }
}
