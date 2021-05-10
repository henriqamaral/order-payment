package com.sacredit.screen


class EnterStoreScreen : Screen() {

  override fun init(oldScreen: Screen?) {
    if(oldScreen != this) {
      println("""
        ################################
        ###  Welcome SACREDIT Store  ###
        ################################
      """)

      println("Please select one option")
      printCommands()
    }
  }

  override fun executeInput(input: String): Screen {
    return when(input) {
      "1" ->  ProductsScreen()
      "2" ->  BasketScreen()
      "99" -> ExitScreen()
      else -> this
    }
  }

  private fun printCommands() {
    println("1  - Show Products")
    println("2  - Show Basket")
    println("99 - Exit")
  }

}