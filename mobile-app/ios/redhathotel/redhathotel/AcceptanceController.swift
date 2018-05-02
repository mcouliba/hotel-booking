//
//  AcceptanceController.swift
//  redhathotel
//
//  Created by Daniel Florian on 4/26/18.
//  Copyright © 2018 Ted Jones - Red Hat. All rights reserved.
//

import UIKit

final class AcceptanceController: UITableViewController {
    
    static let storyboardId = "AcceptanceController"
    
    var user_email = String()
    var userId = 0
    
    @IBOutlet weak var closeButton: UIButton!
    
    override func tableView( _ tableView: UITableView,
                             canEditRowAt indexPath: IndexPath) -> Bool {
        return true
    }
    
    @IBAction func closeButtonClicked(_ sender: Any) {
        
        let reservationsPage = self.storyboard?.instantiateViewController(withIdentifier: "ReservationsViewController") as! ReservationsViewController
        reservationsPage.user_email = self.user_email
        reservationsPage.userId = self.userId
        self.present(reservationsPage, animated: true, completion:  nil)
        
    }
    
    override func tableView( _ tableView: UITableView,
                             didSelectRowAt indexPath: IndexPath ) {
        let indexPath = tableView.indexPathForSelectedRow
        
        let cell = tableView.cellForRow( at: indexPath! )
        var selected = true
        if ( cell?.accessoryType == .checkmark ){
            cell?.accessoryType = .none
            selected = false
        } else {
            cell?.accessoryType = .checkmark
        }
        
        switch indexPath?.row {
        case 0:
            PrefMgr.shared.allowance1 = selected
        case 1:
            PrefMgr.shared.allowance2 = selected
        case 2:
            PrefMgr.shared.allowance3 = selected
        case 3:
            PrefMgr.shared.askForAcceptance = selected
        default: break
        }
        
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.title = "Acceptance"
    }
    
}
