//
//  LoginViewController.swift
//  redhathotel
//
//  Created by Ted Jones - Red Hat on 4/11/18.
//  Copyright © 2018 Ted Jones - Red Hat. All rights reserved.
//

import UIKit

class LoginViewController: UIViewController {

    @IBOutlet weak var username: UITextField!
    
    @IBOutlet weak var password: UITextField!
    
    @IBOutlet weak var signinButton: UIButton!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    

    @IBAction func signinButtonSelected(_ sender: Any) {
        
        let userNameText = username.text
        let passwordText = password.text
        
        if (userNameText?.isEmpty)!
        {
            displayDialog(title: "User name is required", message: "The user name cannot be blank.")
            return
        }
        
        if (passwordText?.isEmpty)!
        {
            displayDialog(title: "Password is required", message: "The password cannot be blank.")
            return
        }
        
        //Create Activity Indicator
        let loginActivityMonitor = UIActivityIndicatorView(activityIndicatorStyle: UIActivityIndicatorViewStyle.whiteLarge)
        
        // Position Activity Indicator in the center of the main view
        loginActivityMonitor.center = view.center
        
        // If needed, you can prevent Acivity Indicator from hiding when stopAnimating() is called
        loginActivityMonitor.hidesWhenStopped = false
        
        // Start Activity Indicator
        loginActivityMonitor.startAnimating()
        
        view.addSubview( loginActivityMonitor )
        
        // Sign in
//        let url = URL(string: "authentication URL")
//        var request = URLRequest(url: url!)
//
//        request.addValue("application/json", forHTTPHeaderField: "content-type")
//        request.addValue("application/json", forHTTPHeaderField: "Accept")
//
//        let postString = ["userName": userNameText!, "userPassword": passwordText!] as [String: String]
//
//        do {
//            request.httpBody = try JSONSerialization.data(withJSONObject: postString, options:.prettyPrinted)
//        } catch let error {
//            print(error.localizedDescription)
//            displayDialog(title: "Error", message: "Something went wrong.")
//            return
//        }
        
        // TODO: remove indicator
        
//        let task = URLSession.shared.dataTask(with: request) { (data: Data?, response: URLResponse?, error: Error?) in
//            self.removeActivityIndicator(activityIndicator: loginActivityMonitor)
//
//            if error != nil
//            {
//                self.displayDialog(title: "Error", message: "Could not perform request.")
//                print("error=\(String(describing: error))")
//                return
//            }
//
//            do {
//                let json = try JSONSerialization.jsonObject(with: data!, options: .mutableContainers) as? NSDictionary
//
//                if let parseJSON = json {
//
//                    //get auth token? Check if empty and throw error/return. Else go to MyReservations page
        
         self.removeActivityIndicator(activityIndicator: loginActivityMonitor)
                    DispatchQueue.main.async {
                        let reservationsPage = self.storyboard?.instantiateViewController(withIdentifier: "ReservationsViewController") as! ReservationsViewController
                        
                        let appDelegate = UIApplication.shared.delegate
                        // replace sign in page
                        appDelegate?.window??.rootViewController = reservationsPage
                        
                        
                        
//                    }
//
//
//                } else {
//                    self.removeActivityIndicator(activityIndicator: loginActivityMonitor)
//
//                    self.displayDialog(title: "Error", message: "Could not perform request")
//                    print(error)
//                }
//
//            } catch {
//
//                self.removeActivityIndicator(activityIndicator: loginActivityMonitor)
//
//                self.displayDialog(title: "Error", message: "Could not perform request")
//                print(error)
//            }
        }
//
//        task.resume()
        
    }
    
    func displayDialog(title: String, message: String) -> Void {
        
        DispatchQueue.main.async {
            let alertController = UIAlertController(title: title, message: message, preferredStyle: .alert)
            
            let OkAction = UIAlertAction(title: "Ok", style: .default)
            { (action:UIAlertAction!) in
                DispatchQueue.main.async
                    {
                        self.dismiss(animated: true, completion: nil)
                        
                }
            }
            
            alertController.addAction(OkAction)
            self.present(alertController, animated: true, completion:  nil)
        }
    }
    
    func removeActivityIndicator(activityIndicator: UIActivityIndicatorView)
    {
        DispatchQueue.main.async {
            activityIndicator.stopAnimating()
            activityIndicator.removeFromSuperview()
        }
    }
}
