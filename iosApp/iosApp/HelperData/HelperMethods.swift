//
//  HelperMethods.swift
//  Novo
//
//  Created by Kunal Gupta on 07/05/18.
//  Copyright © 2018 Novo Financial Corp. All rights reserved.
//

import Foundation

public class HelperMethods {
    public func getEncrypted(_ password: String, key: String) -> String {
        if let keyData = key.data(using: .utf8),
          let publicKeyData = RSAEncryption.convertPEMToDER(keyData),
          let encrypted = RSAEncryption.encryptMessageRSA(message: password, publicKeyData: publicKeyData) {
          return encrypted.base64EncodedString()
        }
        return ""
      }
}
