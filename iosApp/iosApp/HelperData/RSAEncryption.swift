//
//  Encryption.swift
//  RSADemo
//
//  Created by SagarKalathil on 14/02/25.
//

import Foundation
import Security

public class RSAEncryption {
    public static func encryptMessageRSA(message: String, publicKeyData: Data) -> Data? {
        guard let publicKey = createPublicKey(from: publicKeyData) else {
            print("Invalid Public Key")
            return nil
        }

        let messageData = Data(message.utf8)
        var error: Unmanaged<CFError>?

        guard let encryptedData = SecKeyCreateEncryptedData(
            publicKey,
            .rsaEncryptionOAEPSHA1, // RSA with OAEP SHA-256 padding
            messageData as CFData,
            &error
        ) else {
            print("Encryption Error: \(error!.takeRetainedValue() as Error)")
            return nil
        }

        return encryptedData as Data
    }

    public static func decryptMessageRSA(encryptedData: Data, privateKeyData: Data) -> String? {
        guard let privateKey = createPrivateKey(from: privateKeyData) else {
            print("Invalid Private Key")
            return nil
        }

        var error: Unmanaged<CFError>?

        guard let decryptedData = SecKeyCreateDecryptedData(
            privateKey,
            .rsaEncryptionOAEPSHA256,
            encryptedData as CFData,
            &error
        ) else {
            print("Decryption Error: \(error!.takeRetainedValue() as Error)")
            return nil
        }

        return String(data: decryptedData as Data, encoding: .utf8)
    }
    
    private static func createPrivateKey(from keyData: Data) -> SecKey? {
            let options: [String: Any] = [
                kSecAttrKeyType as String: kSecAttrKeyTypeRSA,
                kSecAttrKeyClass as String: kSecAttrKeyClassPrivate,
                kSecAttrKeySizeInBits as String: 2048
            ]
            
            return SecKeyCreateWithData(keyData as CFData, options as CFDictionary, nil)
    }
    
    private static func createPublicKey(from keyData: Data) -> SecKey? {
        let options: [String: Any] = [
            kSecAttrKeyType as String: kSecAttrKeyTypeRSA,
            kSecAttrKeyClass as String: kSecAttrKeyClassPublic,
            kSecAttrKeySizeInBits as String: 2048
        ]

        let derKeyData = convertPEMToDER(keyData) ?? keyData
        
        return SecKeyCreateWithData(derKeyData as CFData, options as CFDictionary, nil)
    }
    
    public static func convertPEMToDER(_ pemData: Data) -> Data? {
        guard let pemString = String(data: pemData, encoding: .utf8) else { return nil }
        
        let keyString = pemString
            .replacingOccurrences(of: "-----BEGIN PUBLIC KEY-----", with: "")
            .replacingOccurrences(of: "-----END PUBLIC KEY-----", with: "")
            .replacingOccurrences(of: "\n", with: "")
        
        return Data(base64Encoded: keyString)
    }
}

