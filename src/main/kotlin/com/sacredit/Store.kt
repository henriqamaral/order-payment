package com.sacredit

import com.sacredit.order.Order
import com.sacredit.order.OrderItem


class Store {

  companion object {
    val basket = mutableListOf<OrderItem>()
    var currentOrder : Order? = null
  }
}