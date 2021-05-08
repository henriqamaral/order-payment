package com.sacredit.payment

import com.sacredit.customer.Address
import com.sacredit.order.Order
import com.sacredit.shipping.PhysicalShipping
import com.sacredit.shipping.Shipping
import com.sacredit.shipping.ShippingFactory

data class Invoice(val order: Order) {

    val billingAddress: Address = order.address
    val shippingAddress: Address = order.address

    val shipping = order.getShipping()


}