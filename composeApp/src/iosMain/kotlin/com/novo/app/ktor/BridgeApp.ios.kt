package com.novo.app.ktor

import com.novo.kmp.BridgeAppTest.KMPLibHelper
import kotlinx.cinterop.ExperimentalForeignApi

actual class SwiftHelper actual constructor() {
    // Instantiate the Swift class (available via the generated Objective-C header)
//    private val helper = KMPLibHelper()


    @OptIn(ExperimentalForeignApi::class)
    actual fun callOther(first: String, second: String): String {
        val key = "-----BEGIN PUBLIC KEY-----MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAzZFTecixiqm4zNFUOhTLawkGdYdznPfDKjNAH6PzwvGKMwXlvbzLN1gOe57g5CIY2fXiPeuWe+f5OulnmNtPZrNA5yfNq2UfNWCol2tU3+o3IVJwkQ6ymvWEhwedNfAJilWs6ABfpp4AN9aH0jzU5AViGrRO6+ygSSOFMSNFB+mYLIqrN48hrotE/j3m1unUjfliaqwryWrJVpgHvWXKj5afPseZnO6nGHZ4x/VZZKYLG2OXt7twDKzgxO7EcgDDODkvl0e5gCPR0VlgGI1KVzubXNtxL3BDhJT5VV498GW0p9r/pRrbR/9xH4z7QJCjCMMdxS5T49i7eiVkZ1SyGQIDAQAB-----END PUBLIC KEY-----"
        val a = KMPLibHelper().callOther("HelloWorld",key)
        return a
    }
}