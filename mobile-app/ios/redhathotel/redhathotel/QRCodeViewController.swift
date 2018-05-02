//
//  QRCodeViewController.swift
//  redhathotel
//
//  Created by Ted Jones - Red Hat on 4/11/18.
//  Copyright © 2018 Ted Jones - Red Hat. All rights reserved.
//

import UIKit

class QRCodeViewController: UIViewController {
    
    @IBOutlet weak var doneButton: UIBarButtonItem!
    @IBOutlet weak var keyImage: UIImageView!
    @IBOutlet weak var checkoutButton: UIBarButtonItem!
    
    var status = String()
    var reservationId = String()
    
    override func viewWillAppear(_ animated: Bool) {
        if ( status == "RESERVED") {
            self.keyImage.image = UIImage()
            checkinPrompt(title: "Check in?", message: "Would you like to check in now? Your room key will be generated.")
        }
        self.navigationController?.navigationBar.isHidden = false
    }

    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    @IBAction func checkoutClicked(_ sender: Any) {
        checkoutPrompt(title: "Check out?", message: "Are you sure you want to check out? We will miss you!")
    }
    
    @IBAction func clickedDone(_ sender: Any) {
            
            self.dismiss(animated: true, completion: nil)
    }
    
    func checkOut() {
        
        //Create Activity Indicator
        let reservationsActivityMonitor = UIActivityIndicatorView(activityIndicatorStyle: UIActivityIndicatorViewStyle.gray)
        
        // Position Activity Indicator in the center of the main view
        reservationsActivityMonitor.center = view.center
        
        // If needed, you can prevent Acivity Indicator from hiding when stopAnimating() is called
        reservationsActivityMonitor.hidesWhenStopped = false
        
        // Start Activity Indicator
        reservationsActivityMonitor.startAnimating()
        
        view.addSubview( reservationsActivityMonitor )
        
        let errorMessage = "Check out failed"
        let errorMessageTitle = "Error"
        let noReservationsFound = "You have no reservations"
        let noReservationsFoundMessageTitle = "No Reservations Found"
        
        // Define base URL
        let baseUrl = "http://reservation-service-hotelbooking.apps.46.4.112.21.xip.io/reservation/checkout"
        // Add parameter
        let urlWithParams = baseUrl + "?reservationid=\(self.reservationId)"
        // Create URL Ibject
        let myUrl = URL(string: urlWithParams);
        
        // Create URL Request
        var request = URLRequest(url:myUrl!);
        
        // Set request HTTP method to GET. It could be POST as well
        request.httpMethod = "GET"
        
        // Excute HTTP Request
        let task = URLSession.shared.dataTask(with: request) {
            data, response, error in
            
            // Check for error
            if error != nil
            {
                self.removeActivityIndicator( activityIndicator: reservationsActivityMonitor )
                self.displayDialog( title: errorMessageTitle, message: errorMessage )
                return
            }
            
            let responseString = NSString(data: data!, encoding: String.Encoding.utf8.rawValue)
            if (responseString == nil) {
                self.removeActivityIndicator( activityIndicator: reservationsActivityMonitor )
                print("checkin request failed")
                self.displayDialog(title: errorMessageTitle, message: errorMessage)
            } else {
                if (responseString?.contains("status = 404"))! {
                    self.displayDialog(title: noReservationsFoundMessageTitle, message: noReservationsFound)
                    self.removeActivityIndicator( activityIndicator: reservationsActivityMonitor )
                }
            }
            
            do {
                guard let resResponse  = String(data: data!, encoding: String.Encoding.utf8) as String?
                else
                {
                        self.displayDialog(title: errorMessageTitle, message: (responseString?.substring(to: 50))! + " Please see log for details.")
                        self.removeActivityIndicator( activityIndicator: reservationsActivityMonitor )
                        return
                }
                if (resResponse == "true") {
                    self.removeActivityIndicator( activityIndicator: reservationsActivityMonitor )
                    self.displayDialog(title: "Checked out!", message: "Thank you for staying at the Red Hat Hotel! \nYour receipt has been emailed to you.")
                } else {
                    self.displayDialog(title: errorMessageTitle, message: (responseString?.substring(to: 50))! + " Please see log for details.")
                    self.removeActivityIndicator( activityIndicator: reservationsActivityMonitor )
                    return
                }
            }
            
        }
        
        task.resume()
        
    }
    
    func checkIn() {
        
        //Create Activity Indicator
        let reservationsActivityMonitor = UIActivityIndicatorView(activityIndicatorStyle: UIActivityIndicatorViewStyle.white)
        
        // Position Activity Indicator in the center of the main view
        reservationsActivityMonitor.center = view.center
        
        // If needed, you can prevent Acivity Indicator from hiding when stopAnimating() is called
        reservationsActivityMonitor.hidesWhenStopped = false
        
        // Start Activity Indicator
        reservationsActivityMonitor.startAnimating()
        
        view.addSubview( reservationsActivityMonitor )
        
        let errorMessage = "Check in failed"
        let errorMessageTitle = "Error"
        let noReservationsFound = "You have no reservations"
        let noReservationsFoundMessageTitle = "No Reservations Found"
        
        // Define base URL
        let baseUrl = "http://reservation-service-hotelbooking.apps.46.4.112.21.xip.io/reservation/checkin"
        // Add parameter
        let urlWithParams = baseUrl + "?reservationid=\(self.reservationId)"
        // Create URL Ibject
        let myUrl = URL(string: urlWithParams);
        
        // Create URL Request
        var request = URLRequest(url:myUrl!);
        
        // Set request HTTP method to GET. It could be POST as well
        request.httpMethod = "GET"
        
        // Excute HTTP Request
        let task = URLSession.shared.dataTask(with: request) {
            data, response, error in
            
            // Check for error
            if error != nil
            {
                self.removeActivityIndicator( activityIndicator: reservationsActivityMonitor )
                self.displayDialog( title: errorMessageTitle, message: errorMessage )
                return
            }
            
            let responseString = NSString(data: data!, encoding: String.Encoding.utf8.rawValue)
            if (responseString == nil) {
                self.removeActivityIndicator( activityIndicator: reservationsActivityMonitor )
                print("checkin request failed")
                self.displayDialog(title: errorMessageTitle, message: errorMessage)
            } else {
                if (responseString?.contains("status = 404"))! {
                    self.displayDialog(title: noReservationsFoundMessageTitle, message: noReservationsFound)
                    self.removeActivityIndicator( activityIndicator: reservationsActivityMonitor )
                }
            }
            
            do {
                guard let resResponse  = String(data: data!, encoding: String.Encoding.utf8) as String?
               // guard let resResponse =  try JSONSerialization.jsonObject(with: data!, options: [ .allowFragments]) as? [String: Any],
                    else {
                        self.displayDialog(title: errorMessageTitle, message: (responseString?.substring(to: 50))! + " Please see log for details.")
                        self.removeActivityIndicator( activityIndicator: reservationsActivityMonitor )
                        return
                }
                if (resResponse == "true") {
                    DispatchQueue.main.async
                    {
                        self.keyImage.image = UIImage(named: "qr-code")
                            
                    }
                    self.removeActivityIndicator( activityIndicator: reservationsActivityMonitor )
                    self.displayCheckedInDialog(title: "Checked in!", message: "Welcome to the Red Hat Hotel! Hold your digital key in front of your door scanner for entry.")
                } else {
                    self.displayDialog(title: errorMessageTitle, message: (responseString?.substring(to: 50))! + " Please see log for details.")
                    self.removeActivityIndicator( activityIndicator: reservationsActivityMonitor )
                    return
                }
            } 
            
        }
        
        task.resume()
        
        
    }
    
    func checkinPrompt(title: String, message: String) -> Void {
        
        DispatchQueue.main.async {
            let alertController = UIAlertController(title: title, message: message, preferredStyle: .alert)
            
            //OK button
            let OkAction = UIAlertAction(title: "Ok", style: .default)
            { (action:UIAlertAction!) in
                DispatchQueue.main.async
                {
                        self.checkIn()
                }
            }
           
            alertController.addAction(OkAction)
            
            // Create Cancel button
            let cancelAction = UIAlertAction(title: "Cancel", style: .cancel) { (action:UIAlertAction!) in
                DispatchQueue.main.async
                {
                    self.dismiss(animated: true, completion: nil)
                        
                }
            }
            alertController.addAction(cancelAction)
            
            
            self.present(alertController, animated: true, completion:  nil)
        }
    }
    
    func displayDialog(title: String, message: String) -> Void {
        
        DispatchQueue.main.async {
            let alertController = UIAlertController(title: title, message: message, preferredStyle: .alert)
            
            let OkAction = UIAlertAction(title: "Ok", style: .default)
            { (action:UIAlertAction!) in
                // Nothing to do here
            }
            
            alertController.addAction(OkAction)
            self.present(alertController, animated: true, completion:  nil)
        }
    }
    
    func displayCheckedInDialog(title: String, message: String) -> Void {
        
        DispatchQueue.main.async {
            let alertController = UIAlertController(title: title, message: message, preferredStyle: .alert)
            
            let OkAction = UIAlertAction(title: "Ok", style: .default)
            { (action:UIAlertAction!) in
                DispatchQueue.main.async
                    {
                       // self.dismiss(animated: true, completion: nil)
                        
                }
            }
            
            alertController.addAction(OkAction)
            self.present(alertController, animated: true, completion:  nil)
        }
    }
    
    func displayCheckOutDialog(title: String, message: String) -> Void {
        
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
    
    func checkoutPrompt(title: String, message: String) -> Void {
        
        DispatchQueue.main.async {
            let alertController = UIAlertController(title: title, message: message, preferredStyle: .alert)
            
            //OK button
            let OkAction = UIAlertAction(title: "Yes", style: .default)
            { (action:UIAlertAction!) in
                DispatchQueue.main.async
                    {
                        self.checkOut()
                }
            }
            
            alertController.addAction(OkAction)
            
            // Create Cancel button
            let cancelAction = UIAlertAction(title: "Not Yet", style: .cancel) { (action:UIAlertAction!) in
                DispatchQueue.main.async
                    {
                        //do nothing
                        
                    }
            }
            alertController.addAction(cancelAction)
            
            
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
