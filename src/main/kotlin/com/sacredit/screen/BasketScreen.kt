package com.sacredit.screen

import com.sacredit.Store

class BasketScreen : Screen() {

  override fun init(oldScreen: Screen?) {
    if(oldScreen != this)
      printBasket()
  }

  override fun executeInput(input: String): Screen {
    return when(input) {
      "0" ->  EnterStoreScreen()
      "1" ->  ProductsScreen()
      "3" ->  payScreen()
      "4" ->  cleanBasket()
      "99" -> ExitScreen()
      else -> this
    }
  }

  private fun cleanBasket(): Screen {
    Store.basket.clear()
    println("Basket cleaned")
    return this
  }

  private fun payScreen() : Screen {
    return if(Store.basket.isNotEmpty()) {
      return OrderScreen()
    } else {
      println("Basket is empty")
      this
    }
  }

  private fun printBasket() {

    if(Store.basket.isNotEmpty()) {
      println("""
      ################################
      ###         Basket           ###
      ################################
      """)

      Store.basket.forEach {
        println("name: ${it.product.name} - price: ${it.product.price} - quantity: ${it.quantity}")
      }
    } else {
      println("Basket is empty")
    }

    printCommands()
  }

  private fun printCommands() {
    println("0  - Back To Initial Page")
    println("1  - Show Products")
    println("3  - Checkout")
    println("4  - Clean Basket")
    println("99 - Exit")
  }

}