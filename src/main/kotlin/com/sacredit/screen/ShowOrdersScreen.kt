package com.sacredit.screen

import com.sacredit.database.OrdersDB


class ShowOrdersScreen : Screen() {

  override fun init(oldScreen: Screen?) {
    printOrders()
  }

  private fun printOrders() {

    println(
      """
      ################################
      ###         Orders           ###
      ################################
      """
    )

    OrdersDB.orders.forEach {
      println("################################")
      it.items.forEach { orderItem ->
        println("name: ${orderItem.product.name} - price: ${orderItem.product.price} - quantity: ${orderItem.quantity}")
      }
      println("################################")
      println("Total: ${it.totalAmount}")
      println("################################")
    }
    printCommands()
  }

  override fun executeInput(input: String) : Screen {
    return when(input) {
      "0" ->  EnterStoreScreen()
      "1" ->  ProductsScreen()
      "4" ->  BasketScreen()
      "99" -> ExitScreen()
      else -> this
    }
  }

  private fun printCommands() {
    println("0  - Back To Initial Page")
    println("1  - Show Products")
    println("3  - Show Basket")
    println("99 - Exit")
  }
}