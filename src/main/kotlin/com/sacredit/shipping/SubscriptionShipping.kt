package com.sacredit.shipping

import com.sacredit.customer.Customer
import com.sacredit.notification.Notification

open class SubscriptionShipping(customer: Customer) : Shipping() {

  open var notification: Notification = Notification(customer.email, "Here are you subscription data")

  override fun generateShip() {
    notification.send()
  }

}