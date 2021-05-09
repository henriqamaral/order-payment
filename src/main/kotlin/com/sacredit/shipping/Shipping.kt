package com.sacredit.shipping

abstract class Shipping {

  var shipped = false

  fun ship() {
    if(shipped) {
      throw Exception("Item(s) already shipped")
    }
    shipItem()
    shipped = true
  }

  abstract fun shipItem()
}