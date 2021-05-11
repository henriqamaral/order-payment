package com.sacredit.database

import com.sacredit.customer.Customer

class UsersDB {

  companion object {
    val users : MutableMap<String, Customer> = mutableMapOf()
  }
}