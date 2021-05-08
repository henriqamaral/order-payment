package com.sacredit.order

import com.sacredit.customer.Address
import com.sacredit.customer.Customer
import com.sacredit.payment.Payment
import com.sacredit.payment.PaymentMethod
import com.sacredit.product.Product
import com.sacredit.shipping.Shipping
import com.sacredit.shipping.ShippingFactory
import java.util.*

class Order(val customer: Customer, val address: Address) {

    val items = mutableListOf<OrderItem>()
    var closedAt: Date? = null
        private set
    var payment: Payment? = null
        private set
    val totalAmount
        get() = items.sumByDouble { it.total }
    var shippings: List<Shipping>? = null
        private set

    fun addProduct(product: Product, quantity: Int) {
        val productAlreadyAdded = items.any { it.product == product }
        if (productAlreadyAdded)
            throw Exception("The product have already been added. Change the amount if you want more.")

        items.add(OrderItem(product, quantity))
    }

    fun pay(method: PaymentMethod) {
        if (payment != null)
            throw Exception("The order has already been paid!")

        if (items.count() == 0)
            throw Exception("Empty order can not be paid!")

        payment = Payment(this, method)

        close()
    }

    private fun close() {
        closedAt = Date()
    }

    fun ship() {

        if (payment == null)
            throw Exception("No payment found")

        shippings = items.map {
            val shipping = ShippingFactory.createShipping(it.product.type, customer, address)
            shipping.ship()
            shipping
        }
    }
}