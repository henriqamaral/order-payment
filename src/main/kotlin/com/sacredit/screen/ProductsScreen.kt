package com.sacredit.screen

import com.sacredit.Store
import com.sacredit.database.ProductsDB
import com.sacredit.order.OrderItem


class ProductsScreen : Screen() {

  override fun init(oldScreen: Screen?) {
    if(oldScreen != this) {
      println("To pick a product type its key: P2 and quantity by underscore: Ex:")
      println("P1_2")
      println("Or type a different option\n")

      printProducts()
    }
  }

  override fun executeInput(input: String): Screen {

    val inputSplit = input.split("_")

    return if(inputSplit.size == 2) {
      addProductToBasket(inputSplit[0], inputSplit[1])
    } else
      when(input) {
        "0" ->  EnterStoreScreen()
        "2" ->  BasketScreen()
        "99" -> ExitScreen()
        else -> this
      }
  }

  private fun addProductToBasket(productKey: String, quantityAsString: String) : Screen {

    if(ProductsDB.getProducts().containsKey(productKey)) {
      val product = ProductsDB.getProducts()[productKey]
      val quantityItem = quantityAsString.toIntOrNull()
      if(quantityItem != null) {
        product?.let {
          Store.basket.add(OrderItem(it, quantityItem))
        }
        println("Product ${product?.name} added to the basket")
      } else {
        println("Wrong quantity value")
      }
    } else {
      println("Product not found")
    }
    return this
  }

  private fun printProducts() {
    ProductsDB
      .getProducts()
      .forEach {
        println("${it.key} - name: ${it.value.name} - price: ${it.value.price}")
      }
    println("\n")
    printCommands()
  }
  private fun printCommands() {
    println("0  - Back To Initial Page")
    println("2  - Show Basket")
    println("99 - Exit")
  }

}