package com.sacredit.order

import com.sacredit.product.Product
import com.sacredit.product.ProductType

data class OrderItem(val product: Product, val quantity: Int) {
    val total get() = product.price * quantity

    val isDigital = product.type == ProductType.DIGITAL ||  product.type == ProductType.MEMBERSHIP

    fun getProductType(): ProductType = product.type


}