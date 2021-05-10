import com.sacredit.customer.Address
import com.sacredit.customer.Customer
import com.sacredit.order.Order
import com.sacredit.payment.CreditCard
import com.sacredit.product.ProductFactory
import com.sacredit.product.ProductType

fun main(args: Array<String>) {

  val shirt = ProductFactory.createProduct("Flowered t-shirt", ProductType.PHYSICAL, 35.00)
  val netflix = ProductFactory.createProduct("Familiar plan", ProductType.MEMBERSHIP, 29.90)
  val book =
    ProductFactory.createProduct("The Hitchhiker's Guide to the Galaxy", ProductType.BOOK, 120.00)
  val music = ProductFactory.createProduct("Stairway to Heaven", ProductType.DIGITAL, 5.00)

  val order = Order(Customer("Sherlock", "holme@holmes.com"), Address("Baker Street"))

  order.addProduct(shirt, 2)
  order.addProduct(netflix, 1)
  order.addProduct(book, 1)
  order.addProduct(music, 1)

  order.pay(CreditCard("43567890-987654367"))

  order.invoice()

  order.ship()
}
