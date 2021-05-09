package com.sacredit.subscription

class Subscribe(private val serviceName: String, private val email: String) {

  fun subscribe() {
    println("Service: $serviceName - subscribed for: $email")
  }

}