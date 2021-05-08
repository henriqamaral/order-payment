package com.sacredit.shipping

abstract class Shipping {

  var shipped = false
  var label: String? = null

  abstract fun generateShip()

  fun ship() {
    generateShip()
    shipped = true
  }
}