//
//  ViewController.swift
//  HelloWorldTwitter
//
//  Created by IFPB on 13/12/2019.
//  Copyright © 2019 IFPB. All rights reserved.
//

import UIKit
import TwitterKit

class MainViewController: UIViewController {
    @IBOutlet weak var btTwitter: UIButton!
    
    
    @IBAction func publicar(_ sender: Any) {
        let composer = TWTRComposer()
        
        composer.setText("Olá mundo do Twitter!! - (message sent by an iOS simulador)")
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

