//
//  ReservationsViewController.swift
//  redhathotel
//
//  Created by Ted Jones - Red Hat on 4/16/18.
//  Copyright © 2018 Ted Jones - Red Hat. All rights reserved.
//

import UIKit

class ReservationsViewController: UIViewController {
    
    let testJson = TestData.jsonText
    
    var reservationsArray: [ Reservation ] = [ Reservation ]()

    @IBOutlet weak var signOutButton: UIButton!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        //TODO: Get JSON string from REST service
        
        //Test Data
        var dictionary:NSDictionary?
        if let data = testJson.data(using: .utf8) {
            
            do {
                dictionary = try! JSONSerialization.jsonObject(with: data, options: .mutableLeaves) as! NSDictionary
            }
        }
        
        let reservations = dictionary!["reservations"] as? [[String: Any?]]
        for reservationDict in reservations! {
            let reservation = Reservation()
            reservation.hotelName = reservationDict["hotelName"]! as! String
            reservation.checkin = reservationDict["checkin"]! as! String
            reservation.checkout = reservationDict["checkout"]! as! String
            reservation.status = reservationDict["status"]! as! String
            reservationsArray.append( reservation )
        }
           
        // Do any additional setup after loading the view.
    }

    @IBAction func signOutAction(_ sender: Any) {
        
        let signInPage = self.storyboard?.instantiateViewController(withIdentifier: "LoginViewController") as! LoginViewController
        
        let appDelegate = UIApplication.shared.delegate
        // replace with sign in page
        appDelegate?.window??.rootViewController = signInPage
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
}
    
extension ReservationsViewController: UITableViewDataSource {
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        
        let cell = tableView.dequeueReusableCell(withIdentifier: ReservationsTableViewCell.reuseIdentifier, for: indexPath as IndexPath) as! ReservationsTableViewCell
        
        let currentReservation = reservationsArray[indexPath.row]
        cell.hotelName?.text = currentReservation.hotelName
        cell.datesLabel?.text = currentReservation.checkin + " - " + currentReservation.checkout
        if ( currentReservation.status == "checkedin" ) {
            cell.keyButton?.setImage(UIImage(named: "key-icon"), for: .normal)
        } else {
            cell.keyButton?.setTitle("Check in", for: .normal)
        }
        
        return cell
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.reservationsArray.count
    }
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }
}

class Reservation: NSObject {
    
    var hotelName: String = String()
    var checkin: String = String()
    var checkout: String = String()
    var status: String = String()
    
}


