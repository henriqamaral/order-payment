package com.sacredit.database

import com.sacredit.customer.Address

class AddressDB {

  companion object {
    val addresses : MutableMap<String, Address> = mutableMapOf()
  }
}