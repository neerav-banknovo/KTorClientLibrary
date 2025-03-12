package com.novo.app.ktor

expect class SwiftHelper() {
    fun callOther(first: String, second: String): String
}

fun useSwiftHelper(first: String, second: String): String {
    val helper = SwiftHelper()  // Dependency manually created without KOIN
    return helper.callOther(first, second)
}
