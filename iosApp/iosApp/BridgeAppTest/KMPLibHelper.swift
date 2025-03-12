//
//  KMPLibHelper.swift
//  iosApp
//
//  Created by Neerav on 12/03/25.
//  Copyright © 2025 orgName. All rights reserved.
//
import Foundation

@objc public class KMPLibHelper: NSObject {
    @objc public func callOther(_ first: String, _ second: String) -> String {
        return HelperMethods.getEncrypted(first, key: second)
    }
}
