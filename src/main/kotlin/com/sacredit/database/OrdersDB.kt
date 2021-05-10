package com.sacredit.database

import com.sacredit.order.Order

class OrdersDB {

  companion object {
    val orders = mutableListOf<Order>()
  }

}