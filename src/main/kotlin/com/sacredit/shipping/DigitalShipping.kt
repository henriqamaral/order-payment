package com.sacredit.shipping

import com.sacredit.customer.Customer
import com.sacredit.notification.Notification

class DigitalShipping(customer: Customer) : SubscriptionShipping(customer) {

  override var notification = Notification(customer.email, "Here are your download link\nAnd a voucher of 10% for you next buy")
}