package com.sacredit.shipping

import com.sacredit.order.Order
import com.sacredit.order.OrderItem
import com.sacredit.product.ProductType

open class DigitalShipping(
  private val order: Order,
  private val shippingItems: List<OrderItem>
) : Shipping() {

  val email = order.customer.email
  val message =
    shippingItems.joinToString("\n") {
      generateNotificationMessage(
        it.product.type,
        it.product.name
      )
    } + if (shippingItems.any { it.product.type == ProductType.DIGITAL }) "\nHere a voucher of 10% for your next buy: VOUCHER_CODE" else ""


  private fun generateNotificationMessage(productType: ProductType, productName: String): String {
    return when (productType) {
      ProductType.MEMBERSHIP -> "Here are you subscription data of the service: $productName"
      ProductType.DIGITAL -> "Here are your download link for: $productName"
      else -> throw Exception("Wrong product type")
    }

  }

}