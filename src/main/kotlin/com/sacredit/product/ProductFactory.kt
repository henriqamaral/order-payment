package com.sacredit.product

class ProductFactory {

  companion object {
    fun createProduct(name: String, productType: ProductType, price: Double): Product =
      when (productType) {
        ProductType.PHYSICAL -> Product(name, productType, price)
        ProductType.BOOK -> Product(name, productType, price)
        ProductType.MEMBERSHIP -> Product(name, productType, price)
        ProductType.DIGITAL -> Product(name, productType, price)
      }
  }
}