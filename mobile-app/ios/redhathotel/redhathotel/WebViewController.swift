//
//  ViewController.swift
//  redhathotel
//
//  Created by Ted Jones - Red Hat on 5/1/18.
//  Copyright © 2018 Ted Jones - Red Hat. All rights reserved.
//

import UIKit
import WebKit

class WebViewController: UIViewController, WKNavigationDelegate {
    
    @IBOutlet weak var webView: WKWebView!
    let path = "http://hotel-booking-react-app-hotelbooking.apps.46.4.112.21.xip.io/"
        
    override func loadView() {
        webView = WKWebView()
        webView.navigationDelegate = self
        view = webView
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.webView.allowsBackForwardNavigationGestures = true
        if let url = URL(string: path) {
            self.webView.load(URLRequest(url: url))
        }
    }
}
    
//    override func loadView() {
//        webView = WKWebView()
//        webView.navigationDelegate = self
//        view = webView
//    }
//
//    override func viewDidLoad() {
//        super.viewDidLoad()
//
//        // webView.delegate = self
//       // webView.s = true
//        webView.contentMode = .scaleAspectFit
//
//        let requestObj = NSURLRequest.init(url: URL(string: path)!, cachePolicy: NSURLRequest.CachePolicy.useProtocolCachePolicy, timeoutInterval: 10.0)
//        let conn:NSURLConnection = NSURLConnection(request: requestObj as URLRequest, delegate: self)!
//        conn.start()
//
//        self.loadingUnvalidatedHTTPSPage = true
//        webView.load(requestObj as URLRequest)
//    }
//
//    override func didReceiveMemoryWarning() {
//        super.didReceiveMemoryWarning()
//    }
//
//    // MARK - DELEGATE METHODS WEBVIEW
//    private func webView(webView: UIWebView!, didFailLoadWithError error: NSError!) {
//        print("Webview fail with error \(error)");
//
//        if(error.domain == NSURLErrorDomain){
//            if (error.code == NSURLErrorServerCertificateHasBadDate || error.code == NSURLErrorServerCertificateUntrusted   ||
//                error.code == NSURLErrorServerCertificateHasUnknownRoot || error.code == NSURLErrorServerCertificateNotYetValid) {
//                print("\n ---- :C ....")
//            }
//        }
//    }
//
//    private func webView(webView: UIWebView!, shouldStartLoadWithRequest request: NSURLRequest!, navigationType: UIWebViewNavigationType) ->Bool{
//        print("Webview iniciando");
//        if (self.loadingUnvalidatedHTTPSPage!) {
//            self.connection = NSURLConnection(request: request as URLRequest, delegate: self)
//            self.connection.start();
//            return false;
//        }
//        return true;
//    }
//
//    private func webViewDidStartLoad(webView: UIWebView!) {
//        print("Webview started Loading")
//    }
//
//    private func webViewDidFinishLoad(webView: UIWebView!) {
//        print("Webview did finish load")
//    }
//
//
//    // MARK - NSURLConnectionDelegate methods
//
//    func urlSession(_ session: URLSession, didReceive challenge: URLAuthenticationChallenge, completionHandler: @escaping (URLSession.AuthChallengeDisposition, URLCredential?) -> Void) {
//
//        if challenge.previousFailureCount > 0 {
//            completionHandler(Foundation.URLSession.AuthChallengeDisposition.cancelAuthenticationChallenge, nil)
//        } else if let serverTrust = challenge.protectionSpace.serverTrust {
//            completionHandler(Foundation.URLSession.AuthChallengeDisposition.useCredential, URLCredential(trust: serverTrust))
//        } else {
//            print("unknown state. error: \(challenge.error)")
//            // do something w/ completionHandler here
//        }
//    }
//
//    func connection(_ connection: NSURLConnection, willSendRequestFor challenge: URLAuthenticationChallenge) {
//        let trust:SecTrust = challenge.protectionSpace.serverTrust!;
//        let cred:URLCredential = URLCredential(trust: trust)
//        challenge.sender?.use(cred, for: challenge)
//    }
//
//    func connection(_ connection: NSURLConnection, NSURLConnection response:URLResponse){
//        let requestObj:NSURLRequest = NSURLRequest(url: URL(string: path)!, cachePolicy: NSURLRequest.CachePolicy.returnCacheDataElseLoad, timeoutInterval: 20.0)
//        self.loadingUnvalidatedHTTPSPage = false
//        self.webView.load(requestObj as URLRequest)
//        self.connection.cancel()
//    }
//    
//}
