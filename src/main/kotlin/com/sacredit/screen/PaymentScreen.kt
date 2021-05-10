package com.sacredit.screen

import com.sacredit.Store
import com.sacredit.customer.Address
import com.sacredit.customer.Customer
import com.sacredit.database.OrdersDB
import com.sacredit.order.Order
import com.sacredit.payment.CreditCard

private const val  regex = "^([0-9]+)-([0-9]+)-([0-9]+)-([0-9]+)$"

class PaymentScreen : Screen() {

  override fun init(oldScreen: Screen?) {
    println("""
      ################################
      ## Type Your Number Card here ##
      ################################
      """)
    println("""
      Your number card should be follow this example:
      1-1-1-1
      """)
    printCommands()
  }

  override fun executeInput(input: String): Screen {

    return if(input.matches(Regex(regex))) {
      processOrder(input)
      InvoiceScreen()
    } else
      when(input) {
        "0" ->  EnterStoreScreen()
        "3" ->  PaymentScreen()
        "4" ->  BasketScreen()
        "99" -> ExitScreen()
        else -> this
      }
  }

  private fun processOrder(input: String) {
    val order = Order(Customer("Sherlock Holmes", "email@email.com"), Address("Baker Street"))
    order.items.addAll(Store.basket)
    order.pay(CreditCard(input))
    order.ship()
    Store.currentOrder = order
    Store.basket.clear()
    OrdersDB.orders.add(order)
  }

  private fun printCommands() {
    println("0  - Back To Initial Page")
    println("2  - Show Products")
    println("3  - Show Basket")
    println("99 - Exit")
  }

}
