package com.sacredit.shipping

import com.sacredit.customer.Address
import com.sacredit.customer.Customer
import com.sacredit.product.ProductType

class ShippingFactory {

  companion object {
    fun createShipping(productType: ProductType,customer: Customer,  address: Address): Shipping =
      when (productType) {
        ProductType.PHYSICAL -> PhysicalShipping(customer, address)
        ProductType.BOOK -> NoTaxShipping(customer, address)
        ProductType.MEMBERSHIP -> SubscriptionShipping(customer)
        ProductType.DIGITAL -> DigitalShipping(customer)
      }
  }
}