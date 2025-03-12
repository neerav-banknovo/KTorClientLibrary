package com.novo.app.ktor

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform