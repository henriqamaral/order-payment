package com.sacredit.screen

import com.sacredit.Store
import com.sacredit.customer.Address
import com.sacredit.customer.Customer
import com.sacredit.database.AddressDB
import com.sacredit.database.UsersDB

class UserScreen(private val email: String) : Screen() {

  override fun init(oldScreen: Screen?) {

    if(oldScreen != this) {
      println("Please type your name and address like EX:")
      println("Sherlock Holmes - Baker Street, 21 ")
    }
  }

  override fun executeInput(input: String): Screen {

    val inputSplit = input.split("-")
    return if(inputSplit.size == 2) {
      UsersDB.users[email] = Customer(email = email, name = inputSplit[0])
      AddressDB.addresses[email] = Address(inputSplit[0])
      Store.currentUser = input
      OrderScreen()
    } else
      when(input) {
        "0" ->  EnterStoreScreen()
        "2" ->  BasketScreen()
        "99" -> ExitScreen()
        else -> wrongData()
      }
  }

  private fun wrongData(): Screen {
    println("Wrong data, try again")
    return this
  }

}
