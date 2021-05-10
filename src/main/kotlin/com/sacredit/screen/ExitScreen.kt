package com.sacredit.screen

import kotlin.system.exitProcess


class ExitScreen : Screen() {

  override fun init(oldScreen: Screen?) {
    println("Thank you for buying with us!!!")
    println("Good buy")
    exitProcess(0)

  }

  override fun executeInput(input: String) : Screen {
    return this
  }
}