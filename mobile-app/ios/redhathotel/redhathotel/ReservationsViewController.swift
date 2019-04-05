//
//  ReservationsViewController.swift
//  redhathotel
//
//  Created by Ted Jones - Red Hat on 4/16/18.
//  Copyright © 2018 Ted Jones - Red Hat. All rights reserved.
//

import UIKit
import SafariServices

class ReservationsViewController: UIViewController {
    
    let url = "http://hotel-booking-web-app-hotel-booking-web-app.apps.cndsw.openshiftworkshop.com/"
    
    @IBOutlet weak var tableVIew: UITableView!
    
    @IBOutlet weak var manageReservationsButton: UIButton!
    
    var user_email = String()
    var userId = 0
    
    var reservationsArray: [ Reservation ] = [ Reservation ]()
    
    var currentStatus = String()
    var currentReservationId = String()

    @IBOutlet weak var signOutButton: UIButton!
    
    override func viewWillAppear(_ animated: Bool) {
         self.loadData()
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if let destinationViewController = segue.destination as? QRCodeViewController {
            destinationViewController.status = self.currentStatus
        }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    func loadData() {
        
        //Clear out reservationsArray
        self.reservationsArray.removeAll()
        
        //Create Activity Indicator
        let reservationsActivityMonitor = UIActivityIndicatorView(activityIndicatorStyle: UIActivityIndicatorViewStyle.gray)
        
        // Position Activity Indicator in the center of the main view
        reservationsActivityMonitor.center = view.center
        
        // If needed, you can prevent Acivity Indicator from hiding when stopAnimating() is called
        reservationsActivityMonitor.hidesWhenStopped = false
        
        // Start Activity Indicator
        reservationsActivityMonitor.startAnimating()
        
        view.addSubview( reservationsActivityMonitor )
        
        let errorMessage = "Reservation request failed"
        let errorMessageTitle = "Error"
        let noReservationsFound = "You have no reservations"
        let noReservationsFoundMessageTitle = "No Reservations Found"
        
        // Define base URL
        let baseUrl = "http://reservation-service-hotel-booking-reservation.apps.cndsw.openshiftworkshop.com/reservation/findbycustomerid"
        // Add parameter
        let urlWithParams = baseUrl + "?customerid=\(self.userId)"
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
                print("reservation request failed")
                self.displayDialog(title: errorMessageTitle, message: errorMessage)
            } else {
                if (responseString?.contains("status = 404"))! {
                    self.displayDialog(title: noReservationsFoundMessageTitle, message: noReservationsFound)
                    self.removeActivityIndicator( activityIndicator: reservationsActivityMonitor )
                }
            }
            
            do {
                guard let resResponse =  try JSONSerialization.jsonObject(with: data!, options: []) as? [String: Any],
                    let reservations = resResponse["content"] as? [[String: Any]]
                    else { return }
                
                //looping through all the json objects in the array teams
                for i in 0 ..< reservations.count{
                    let reservation = Reservation()
                    reservation.reservationId = reservations[i]["id"] as! String
                    reservation.hotelName = reservations[i]["hotel_name"] as! String
                    reservation.city = reservations[i]["hotel_city"] as! String
                    reservation.country = reservations[i]["hotel_country"] as! String
                    reservation.checkin = reservations[i]["checkin"] as! NSNumber
                    reservation.checkout = reservations[i]["checkout"] as! NSNumber
                    reservation.status = reservations[i]["status"] as! String
                    self.reservationsArray.append( reservation )
                }
                
                DispatchQueue.main.async{
                    self.reservationsArray = self.reservationsArray.sorted(by: { $0.checkin.doubleValue < $1.checkin.doubleValue })
                    self.tableVIew.reloadData()
                    self.removeActivityIndicator( activityIndicator: reservationsActivityMonitor )
                }
               
                
            } catch {
                self.displayDialog(title: errorMessageTitle, message: errorMessage)
                self.removeActivityIndicator( activityIndicator: reservationsActivityMonitor )
                print(error.localizedDescription)
            }
    
        }
        
        task.resume()
    
        
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
    
    @IBAction func manageReservationsClicked(_ sender: Any) {
        let urlObject =  URL(string: url)
        let svc = SFSafariViewController(url: urlObject!)
        present(svc, animated: true, completion: nil)
    }
}


extension ReservationsViewController: UITableViewDelegate {
    
    func tableView( _ tableView: UITableView,
                             didSelectRowAt indexPath: IndexPath ) {
        let indexPath = tableView.indexPathForSelectedRow
        let cell = tableView.cellForRow( at: indexPath! ) as! ReservationsTableViewCell
        let qrVC = storyboard?.instantiateViewController(withIdentifier: "QRCodeViewController") as! QRCodeViewController
        qrVC.status = cell.status
        qrVC.reservationId = cell.reservationId
        self.present(qrVC, animated: true, completion: nil)
    }
}
    
extension ReservationsViewController: UITableViewDataSource {
    
    fileprivate func getDateForUnixTimestamp(_ timestamp: NSNumber) -> String {
        let epocTime = TimeInterval(truncating: timestamp) / 1000 // convert it from milliseconds dividing it by 1000
        
        let unixTimestamp = NSDate(timeIntervalSince1970: epocTime) //convert unix timestamp to Date
        
        let dateFormatter = DateFormatter()
        dateFormatter.timeZone = NSTimeZone() as TimeZone?
        dateFormatter.locale = NSLocale.current // NSLocale(localeIdentifier: "en_US_POSIX")
        dateFormatter.dateFormat =  "MMM-dd-yyyy"
        dateFormatter.date(from: String(describing: unixTimestamp))
        
        let updatedTimeStamp = unixTimestamp
        return DateFormatter.localizedString(from: updatedTimeStamp as Date, dateStyle: DateFormatter.Style.medium, timeStyle: DateFormatter.Style.none)
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        
        let cell = tableView.dequeueReusableCell(withIdentifier: ReservationsTableViewCell.reuseIdentifier, for: indexPath as IndexPath) as! ReservationsTableViewCell
        
        let currentReservation = reservationsArray[indexPath.row]
        cell.hotelName?.text = currentReservation.hotelName
        
        let checkinDateString = getDateForUnixTimestamp(currentReservation.checkin)
        let checkoutDateString = getDateForUnixTimestamp(currentReservation.checkout)

        cell.datesLabel.text = checkinDateString + " to " + checkoutDateString
        cell.status = currentReservation.status
        cell.reservationId = currentReservation.reservationId
        
        switch currentReservation.status {
        case "RESERVED":
            cell.actionLabel?.text = "Check in"
            cell.actionLabel.textColor = .green
        case "CHECKIN":
            cell.actionLabel.text = "Your Room Key"
            cell.actionLabel.textColor = .yellow
        default:
            cell.actionLabel?.text = "Checked out"
            cell.actionLabel.textColor = .red
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
    
    var reservationId = String()
    var hotelName: String = String()
    var country: String = String()
    var city: String = String()
    var checkin: NSNumber = NSNumber()
    var checkout: NSNumber = NSNumber()
    var status: String = String()
    
}


