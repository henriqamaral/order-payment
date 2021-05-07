package com.sacredit.payment

import com.sacredit.customer.Address
import com.sacredit.order.Order

data class Invoice(val order: Order) {
    val billingAddress: Address = order.address
    val shippingAddress: Address = order.address
}