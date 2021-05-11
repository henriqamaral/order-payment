package com.sacredit.screen

import com.sacredit.Store
import com.sacredit.database.UsersDB

private const val  regex = "^(.+)@(.+)$"

class LoginScreen : Screen() {

  override fun init(oldScreen: Screen?) {

    if(oldScreen != this) {
      println("Please type your email:")
    }


  }

  override fun executeInput(input: String): Screen {

    return if(input.matches(Regex(regex))) {
      if(UsersDB.users.containsKey(input)) {
        Store.currentUser = input
        OrderScreen()
      } else {
        UserScreen(input)
      }
    } else
      when(input) {
        "0" ->  EnterStoreScreen()
        "2" ->  BasketScreen()
        "99" -> ExitScreen()
        else -> wrongEmailMessage()
      }
  }

  private fun wrongEmailMessage(): Screen {
    println("Wrong e-mail typed")
    return this
  }

}
