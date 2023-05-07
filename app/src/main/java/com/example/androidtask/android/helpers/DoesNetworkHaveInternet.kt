package com.example.androidtask.android.helpers

import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket

object DoesNetworkHaveInternet {
    // Make sure to execute this on a background thread.
    fun execute(): Boolean {
        return try {
            val socket = Socket()
            socket.connect(InetSocketAddress("8.8.8.8", 53), 1500)
            socket.close()
            true
        } catch (e: IOException) {
            false
        }
    }
}