//
//  ViewController.swift
//  Memorias
//
//  Created by IFPB on 06/12/2019.
//  Copyright © 2019 IFPB. All rights reserved.
//

import UIKit

class FormViewController: UIViewController {

    @IBOutlet weak var tfAssunto: UITextField!
    @IBOutlet weak var swLike: UISwitch!
    @IBOutlet weak var lbNota: UILabel!
    @IBOutlet weak var tvComentario: UITextView!
    @IBOutlet weak var lbRecomendacao: UILabel!
    @IBOutlet weak var slRecomendacao: UISlider!
    @IBOutlet weak var stNota: UIStepper!
    
    var dao: MemoriaDAO!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.dao = MemoriaDAO()
        
    }
    
    @IBAction func alteraNota(_ sender: Any) {
        self.lbNota.text = String(Int(self.stNota.value))
    }
    
    @IBAction func salvarMemoria(_ sender: Any) {
        let assunto = self.tfAssunto.text
        let like = self.swLike.isOn
        let comentarios = self.tvComentario.text
        let nota = Int(self.stNota.value)
        let recomendacao = Int(self.slRecomendacao.value)
        
        
        if (self.dao.add(assunto: assunto!, like: like, comentarios: comentarios!, nota: nota, recomendacao: recomendacao)){
            
            let alert = UIAlertController(title: "Atenção", message: "Deu certo!", preferredStyle: UIAlertController.Style.alert)
            alert.addAction(UIAlertAction(title: "OK", style: UIAlertAction.Style.default, handler: { (UIAlertAction) in
                self.navigationController?.popViewController(animated: true)
            }))
            self.present(alert, animated: true, completion: nil)
            
            
        }else {
            let alert = UIAlertController(title: "Atenção", message: "Deu problema!", preferredStyle: UIAlertController.Style.alert)
            alert.addAction(UIAlertAction(title: "OK", style: UIAlertAction.Style.cancel, handler: nil))
            self.present(alert, animated: true, completion: nil)
        }
        
    }
    
    @IBAction func alteraRecomendacao(_ sender: Any) {
        self.lbRecomendacao.text = String(Int(self.slRecomendacao.value))
    }
}

