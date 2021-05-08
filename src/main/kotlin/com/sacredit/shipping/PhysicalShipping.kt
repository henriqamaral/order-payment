package com.sacredit.shipping

import com.sacredit.customer.Address
import com.sacredit.customer.Customer

open class PhysicalShipping(private val customer: Customer, private val address: Address) :
  Shipping() {

  override fun generateShip() {
    label = "${customer.name}\n${address.street}"
  }
}
