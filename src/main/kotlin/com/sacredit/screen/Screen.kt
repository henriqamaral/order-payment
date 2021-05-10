package com.sacredit.screen

abstract class Screen {

  abstract fun init(oldScreen: Screen?)

  abstract fun executeInput(input: String) : Screen

}