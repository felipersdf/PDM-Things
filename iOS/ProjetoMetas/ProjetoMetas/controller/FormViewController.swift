//
//  ViewController.swift
//  ProjetoMetas
//
//  Created by IFPB on 10/12/2019.
//  Copyright © 2019 IFPB. All rights reserved.
//

import UIKit
import TwitterKit

class FormViewController: UIViewController {

    @IBOutlet weak var tfMeta: UITextField!
    @IBOutlet weak var swConcluiu: UISwitch!
    
    var dao:MetaDAO!
    
    override func viewDidLoad(){
        super.viewDidLoad()
        self.dao = MetaDAO()
    }
    
    @IBAction func salvar(_ sender: Any) {
        let meta = self.tfMeta.text
        let concluiu = self.swConcluiu.isOn
        
        if(self.dao.add(nome: meta!, concluiu: concluiu)){
            
            let alert = UIAlertController(title: "Atenção", message: "Pegou", preferredStyle: UIAlertController.Style.alert)
            
            alert.addAction(UIAlertAction(title: "Ok", style: UIAlertAction.Style.default, handler: {   (UIAlertAction) in
                self.navigationController?.popViewController(animated: true)
            }))
            self.present(alert, animated: true, completion: nil)
            
        }else {
            let alert = UIAlertController(title: "Atenção", message: "Nao Pegou", preferredStyle: UIAlertController.Style.alert)
            
            alert.addAction(UIAlertAction(title: "Ok", style: UIAlertAction.Style.default, handler: nil))
            self.present(alert, animated: true, completion: nil)
        }
        
    }
    @IBAction func publicarMeta(_ sender: Any) {
        let meta = self.tfMeta.text!
        let composer = TWTRComposer()
        let concluiu = self.swConcluiu.isOn
        
        if(!concluiu){
            let alert = UIAlertController(title: "Calma lá", message: "Você nāo concluiu a sua meta!!", preferredStyle: UIAlertController.Style.alert)
            alert.addAction(UIAlertAction(title: "Ok", style: UIAlertAction.Style.default, handler: nil))
            self.present(alert, animated: true, completion: nil)
            
        }else {
            composer.setText("Eu consegui! Eu cumpri a minha meta de '\(meta)' !!")
            composer.setImage(UIImage(named: "twitterkit"))
            
            // Called from a UIViewController
            composer.show(from: self.navigationController!) { (result) in
                if (result == .done) {
                    print("Successfully composed Tweet")
                    
                } else {
                    print("Cancelled composing")
                }
            }
        }
    }
}

