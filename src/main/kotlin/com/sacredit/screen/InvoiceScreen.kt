package com.sacredit.screen

import com.sacredit.Store

class InvoiceScreen : Screen() {

  override fun init(oldScreen: Screen?) {

    Store.currentOrder?.invoice()
    println("""
      ##############################################################
      ##      Thank you for Buying on SACREDIT Store             ###
      ##############################################################
      # Items               Qtd                              Total #
      ${
      Store.currentOrder?.items?.joinToString("\n") {
      "# ${it.product.name}     ${it.quantity}          ${it.total}#"
      }
    }
      ##############################################################
      #Total: ${Store.currentOrder?.totalAmount}                   #
      ##############################################################
      """)

    printCommands()
  }

  override fun executeInput(input: String): Screen {
    return when(input) {
      "0" ->  EnterStoreScreen()
      "99" -> ExitScreen()
      else -> this
    }
  }

  private fun printCommands() {
    println("0  - Back To Initial Page")
    println("99 - Exit")
  }

}
