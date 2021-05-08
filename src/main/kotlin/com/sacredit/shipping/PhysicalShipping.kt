package com.sacredit.shipping

import com.sacredit.order.Order
import com.sacredit.order.OrderItem
import com.sacredit.product.ProductType
import java.lang.Exception

class PhysicalShipping(private val order: Order, private val shippingItems: List<OrderItem>) :
  Shipping() {

  val shippingLabel = "${order.customer.name}\n" +
      "${order.address.street}\n" +
      shippingItems.joinToString("\n") { generateMessage(it.product.type, it.product.name) }

  private fun generateMessage(productType: ProductType, productName: String): String {

    return when (productType) {
      ProductType.BOOK -> "$productName - The item is tax-exempt"
      ProductType.PHYSICAL -> productName
      else -> throw Exception("Wrong product type")
    }
  }
}
