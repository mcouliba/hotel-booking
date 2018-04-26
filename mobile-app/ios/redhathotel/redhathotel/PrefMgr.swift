//
//  PrefMgr.swift
//  redhathotel
//
//  Created by Daniel Florian on 4/26/18.
//  Copyright © 2018 Ted Jones - Red Hat. All rights reserved.
//

import Foundation

enum Pref: String {

    case askForAcceptance = "askForAcceptance"
    case keepLoggedIn = "keepLoggedIn"

    var id : String {
        get {
            return self.rawValue
        }
    }

}

final class PrefMgr {

    static let shared = PrefMgr()

    private let prefs: UserDefaults

    // don't allow construction outside of this class
    private init() {
        self.prefs = UserDefaults.standard
    }

    var askForAcceptance: Bool {
        get {
            if let value = self.prefs.object( forKey: Pref.askForAcceptance.id ) {
                return value as Bool
            }

            // default value
            return true
        }
        
        set( newValue ) {
            self.prefs.set( newValue, forKey: Pref.askForAcceptance.id )
        }
    }

    var keepLoggedIn: Bool {
        get {
            if let value = self.prefs.object( forKey: Pref.keepLoggedIn.id ) {
                return value as Bool
            }
            
            // default value
            return false
        }
        
        set( newValue ) {
            self.prefs.set( newValue, forKey: Pref.keepLoggedIn.id )
        }
    }

}
