//
//  ViewController.swift
//  Meu Primeiro Projeto
//
//  Created by IFPB on 01/11/2019.
//  Copyright © 2019 IFPB. All rights reserved.
//

import UIKit

class MainController: UIViewController {

    @IBOutlet weak var tfFrase: UITextField!
    @IBOutlet weak var swNota: UISwitch!
    @IBOutlet weak var slNota: UISlider!
    @IBOutlet weak var stNota: UIStepper!
    @IBOutlet weak var lbFrase: UILabel!
    @IBOutlet weak var lbSlider: UILabel!
    @IBOutlet weak var lbStepper: UILabel!

    
    @IBAction func copiar(_ sender: Any) {
        self.lbFrase.text = self.tfFrase.text
    }
    
    @IBAction func definirNotaSlider(_ sender: Any) {
        self.lbSlider.text = String(Int(self.slNota.value))
    }
    
    @IBAction func definirNotaStepper(_ sender: Any) {
        self.lbStepper.text = String(Int(self.stNota.value))
    }
}

