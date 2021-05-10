package com.sacredit.screen

import com.sacredit.Store

class OrderScreen : Screen() {

  override fun init(oldScreen: Screen?) {
    printOderDetails()
  }

  override fun executeInput(input: String): Screen {
    return when(input) {
      "0" ->  EnterStoreScreen()
      "1" ->  ProductsScreen()
      "3" ->  PaymentScreen()
      "4" ->  BasketScreen()
      "99" -> ExitScreen()
      else -> this
    }
  }

  private fun printOderDetails() {

    println("""
      ################################
      ###      Order Detail        ###
      ################################
      """)

    Store.basket.forEach {
      println("name: ${it.product.name} - price: ${it.product.price} - quantity: ${it.quantity}")
    }

    println("""
      ################################
      Total: ${Store.basket.sumByDouble { it.total }}
      ################################
      """)

    printCommands()

  }

  private fun printCommands() {
    println("0  - Back To Initial Page")
    println("1  - Show Products")
    println("3  - Pay")
    println("4  - Show Basket")
    println("99 - Exit")
  }
}