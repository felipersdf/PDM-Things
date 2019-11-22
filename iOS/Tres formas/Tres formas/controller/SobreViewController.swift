//
//  SobreViewController.swift
//  Tres formas
//
//  Created by IFPB on 21/11/2019.
//  Copyright © 2019 IFPB. All rights reserved.
//

import UIKit

class SobreViewController: UIViewController {


    @IBAction func retornoForma2(_ sender: Any) {
        self.dismiss(animated: true, completion: nil)
        
//        self.navigationController?.popViewController(animated: true)
    }

    // Toque na tela
    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
    //    Retorno Transição Forma 1
    //    self.dismiss(animated: true, completion: nil)
        
    // Retorno Trasnsição Forma 3 (Navigation Controller)
    self.navigationController?.popViewController(animated: true)
    
    }
}
