package com.sacredit.database

import com.sacredit.product.Product
import com.sacredit.product.ProductType

class ProductsDB {


  companion object {

    fun getProducts(): Map<String, Product> = mapOf(
      "P1" to Product("Flowered t-shirt", ProductType.PHYSICAL, 35.00),
      "P2" to Product("Familiar plan", ProductType.MEMBERSHIP, 29.90),
      "P3" to Product("The Hitchhiker's Guide to the Galaxy", ProductType.BOOK, 120.00),
      "P4" to Product("Stairway to Heaven", ProductType.DIGITAL, 5.00)
    )
  }
}