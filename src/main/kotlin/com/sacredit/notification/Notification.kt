package com.sacredit.notification

data class Notification(val email: String, val message: String) {

  fun send() {
    println("""
      $email
      $message
    """.trimIndent())
  }
}