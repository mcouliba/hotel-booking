//
//  ReservationsTableViewCell.swift
//  redhathotel
//
//  Created by Ted Jones - Red Hat on 4/17/18.
//  Copyright © 2018 Ted Jones - Red Hat. All rights reserved.
//

import UIKit

class ReservationsTableViewCell: UITableViewCell {
    
   
    static let reuseIdentifier = "ReservationsTableViewCell"

    @IBOutlet weak var hotelName: UILabel!
    @IBOutlet weak var datesLabel: UILabel!
    @IBOutlet weak var keyButton: UIButton!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }


}
