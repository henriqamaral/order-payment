package com.sacredit.shipping

import com.sacredit.order.Order
import com.sacredit.order.OrderItem
import com.sacredit.product.ProductType

class ShippingFactory {

  companion object {
    fun createShipping(
      productType: ProductType,
      order: Order,
      shippingItems: List<OrderItem>
    ): Shipping =
      when (productType) {
        ProductType.PHYSICAL -> PhysicalShipping(order, shippingItems)
        ProductType.BOOK -> PhysicalShipping(order, shippingItems)
        ProductType.MEMBERSHIP -> DigitalShipping(order, shippingItems)
        ProductType.DIGITAL -> DigitalShipping(order, shippingItems)
      }
  }
}