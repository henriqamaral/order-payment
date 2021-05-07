package com.sacredit.order

import com.sacredit.product.Product

data class OrderItem(val product: Product, val quantity: Int) {
    val total get() = 10 * quantity
}