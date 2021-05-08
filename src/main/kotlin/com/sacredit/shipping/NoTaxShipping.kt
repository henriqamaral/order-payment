package com.sacredit.shipping

import com.sacredit.customer.Address
import com.sacredit.customer.Customer

class NoTaxShipping(customer: Customer, address: Address) : PhysicalShipping(customer, address) {

  override fun generateShip() {
    super.generateShip()
    label = "$label\nThe item(s) is/are tax-exempt"
  }

}