//
//  ViewController.swift
//  Papai Noel
//
//  Created by IFPB on 22/11/2019.
//  Copyright © 2019 IFPB. All rights reserved.
//

import UIKit

class FormViewController: UIViewController {

    @IBOutlet weak var tfDescricao: UITextField!
    @IBOutlet weak var tfGrau: UITextField!
    
    @IBAction func salvar(_ sender: Any) {
        let descricao = self.tfDescricao.text!
        let grau = Int(self.tfGrau.text!)
        
        let presente = Presente(descricao: descricao, grau: grau!)
        
        let delegate = UIApplication.shared.delegate as! AppDelegate
        delegate.cadastro.add(presente: presente)
        
        print(delegate.cadastro.get())
        
        self.navigationController?.popViewController(animated: true)
    }
}

