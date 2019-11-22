//
//  ViewController.swift
//  Tres formas
//
//  Created by IFPB on 21/11/2019.
//  Copyright © 2019 IFPB. All rights reserved.
//

import UIKit

class MainViewController: UIViewController {

    
    @IBAction func transicaoForma2(_ sender: Any) {
        let svc = self.storyboard?.instantiateViewController(withIdentifier: "sobre") as! SobreViewController
        self.present(svc, animated: true, completion: nil)
    }
    

}

