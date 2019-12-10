//
//  MetaDAO.swift
//  ProjetoMetas
//
//  Created by IFPB on 10/12/2019.
//  Copyright © 2019 IFPB. All rights reserved.
//

import Foundation
import CoreData
import UIKit

class MetaDAO: NSObject {
    var contexto: NSManagedObjectContext
    
    override init() {
        self.contexto = (UIApplication.shared.delegate as! AppDelegate).persistentContainer.viewContext
    }
    
    func add(nome: String, concluiu:Bool) -> Bool{
        let meta = NSEntityDescription.insertNewObject(forEntityName: "Meta", into: self.contexto)
        
        meta.setValue(nome, forKey: "nome")
        meta.setValue(concluiu, forKey: "concluiu")
        
        do {
            try self.contexto.save()
            return true
        } catch{
            return false
        }
    }
    
    func get() -> Array<Meta>? {
        var lista: [NSManagedObject] = []
        let requisicao = NSFetchRequest<NSFetchRequestResult>(entityName: "Meta")
        
        do {
            try lista = self.contexto.fetch(requisicao) as! [NSManagedObject]
                return lista as! Array<Meta>
        } catch {
        }
        return nil
    }
}
