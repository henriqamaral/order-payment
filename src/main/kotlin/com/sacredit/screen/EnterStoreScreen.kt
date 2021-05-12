package com.sacredit.screen

import com.sacredit.Store


class EnterStoreScreen : Screen() {

  override fun init(oldScreen: Screen?) {
    if (oldScreen != this) {
      println(
        """
        ################################
        ###  Welcome SACREDIT Store  ###
        ################################
      """
      )

      println("Please select one option")
      printCommands()
    }
  }

  override fun executeInput(input: String): Screen {
    return when (input) {
      "1" -> ProductsScreen()
      "2" -> BasketScreen()
      "3" -> ShowOrdersScreen()
      "99" -> ExitScreen()
      "90" -> logout()
      else -> this
    }
  }

  private fun logout(): Screen {
    Store.currentUser = null
    return this
  }

  private fun printCommands() {
    println("1  - Show Products")
    println("2  - Show Basket")
    Store.currentUser?.let {
      println("3  - Show Orders")
      println("90  - Logout")
    }
    println("99 - Exit")
  }

}