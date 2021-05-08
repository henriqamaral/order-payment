import com.sacredit.customer.Address
import com.sacredit.customer.Customer
import com.sacredit.order.Order
import com.sacredit.payment.CreditCard
import com.sacredit.product.ProductFactory
import com.sacredit.product.ProductType
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


class BootstrapTest {

  val customer = Customer("Sherlock", "holme@holmes.com")

  @Test
  fun `when adding product to order`() {
    val order = Order(customer, Address("Baker Street"))
    val music = ProductFactory.createProduct("Product 1", ProductType.DIGITAL, 10.00)
    val book = ProductFactory.createProduct("Product 2", ProductType.BOOK, 11.00)
    val shirt = ProductFactory.createProduct("Product 3", ProductType.PHYSICAL, 12.00)

    order.addProduct(shirt, 1)
    order.addProduct(book, 1)
    order.addProduct(music, 1)

    assertThat(order.items.size).isEqualTo(3)
  }

  @Test
  fun `when total amount is calculated`() {
    val order = Order(customer, Address("Baker Street"))
    val music1 = ProductFactory.createProduct("Product 1", ProductType.DIGITAL, 10.00)
    val book = ProductFactory.createProduct("Product 2", ProductType.BOOK, 15.00)


    order.addProduct(music1, 2)
    order.addProduct(book, 2)

    assertThat(order.totalAmount).isEqualTo(50.0)
  }

  @Test
  fun `when adding different physical products should generate different shipping labels`() {

    //given
    val order = Order(customer, Address("Baker Street"))

    val book = ProductFactory.createProduct("Product 1", ProductType.BOOK, 11.00)
    val shirt = ProductFactory.createProduct("Product 2", ProductType.PHYSICAL, 12.00)

    order.addProduct(shirt, 1)
    order.addProduct(book, 1)

    //when
    order.pay(CreditCard("43567890-987654367"))

    //then
    order.ship()

    assertThat(order.shippings?.size).isEqualTo(2)
    assertThat(order.shippings)
      .hasSize(2)
      .extracting("label")
      .contains(
        "Sherlock\nBaker Street",
        "Sherlock\nBaker Street\nThe item(s) is/are tax-exempt"
      )
  }

  @Test
  fun `when adding different virtual products should generate different shipping labels`() {

    //given
    val order = Order(customer, Address("Baker Street"))

    val netflix = ProductFactory.createProduct("Product 1", ProductType.MEMBERSHIP, 10.00)
    val limboGame = ProductFactory.createProduct("Product 2", ProductType.DIGITAL, 5.00)
    order.addProduct(netflix, 1)
    order.addProduct(limboGame, 1)

    //when
    order.pay(CreditCard("43567890-987654367"))

    //then
    order.ship()

    assertThat(order.shippings)
      .hasSize(2)
      .extracting("notification.email", "notification.message")
      .contains(Assertions.tuple("holme@holmes.com", "Here are you subscription data"),
        Assertions.tuple("holme@holmes.com", "Here are your download link\nAnd a voucher of 10% for you next buy"))

  }
}
