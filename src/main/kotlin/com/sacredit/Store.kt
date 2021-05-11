package com.sacredit

import com.sacredit.order.Order
import com.sacredit.order.OrderItem


class Store {

  companion object {
    val basket = mutableListOf<OrderItem>()
    var currentUser : String? = null
    var currentOrder : Order? = null
  }
}