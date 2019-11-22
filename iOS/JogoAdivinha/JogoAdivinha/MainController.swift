//
//  ViewController.swift
//  JogoAdivinha
//
//  Created by IFPB on 14/11/2019.
//  Copyright © 2019 IFPB. All rights reserved.
//

import UIKit

class MainController: UIViewController {

    
    @IBOutlet weak var lbValorMax: UILabel!
    @IBOutlet weak var lbValorMin: UILabel!
    @IBOutlet weak var lbExibe: UILabel!
    @IBOutlet weak var slValorAtual: UISlider!
    
    @IBAction func defineValor(_ sender: Any) {
        self.lbExibe.text = String(Int(self.slValorAtual.value))
    }
    
    @IBAction func getResultado(_ sender: Any) {
        
        if(Int(self.slValorAtual.value) > self.numero){
            self.lbValorMax.text = String(Int(self.slValorAtual.value))
        }
        
        
    }
    
    @IBAction func novoJogo(_ sender: Any) {
        super.viewDidLoad()
    }
}

