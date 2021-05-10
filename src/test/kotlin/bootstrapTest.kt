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

  private val customer = Customer("Sherlock", "holme@holmes.com")

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
  fun `when adding different physical products should generate one shipping`() {

    //given
    val order = Order(customer, Address("Baker Street"))

    val book = ProductFactory.createProduct("Product 1", ProductType.BOOK, 11.00)
    val shirt = ProductFactory.createProduct("Product 2", ProductType.PHYSICAL, 12.00)

    order.addProduct(shirt, 1)
    order.addProduct(book, 1)

    //when
    order.pay(CreditCard("43567890-987654367"))

    //then
    val shipping = order.getShipping()

    assertThat(shipping)
      .hasSize(1)
      .extracting("shippingLabel")
      .contains(
            "Sherlock\n" +
            "Baker Street\n" +
            "Product 2\n" +
            "Product 1 - The item is tax-exempt"
      )
  }

  @Test
  fun `when adding different digital products should generate different one shipping`() {

    //given
    val order = Order(customer, Address("Baker Street"))

    val netflix = ProductFactory.createProduct("Product 1", ProductType.MEMBERSHIP, 10.00)
    val limboGame = ProductFactory.createProduct("Product 2", ProductType.DIGITAL, 5.00)
    order.addProduct(netflix, 1)
    order.addProduct(limboGame, 1)

    //when
    order.pay(CreditCard("43567890-987654367"))

    //then
    val shipping = order.getShipping()

    assertThat(shipping)
      .hasSize(1)
      .extracting("email", "message")
      .contains(
        Assertions.tuple(
          "holme@holmes.com", "Here are you subscription data of the service: Product 1\n" +
              "Here are your download link for: Product 2\n" +
              "Here a voucher of 10% for your next buy: VOUCHER_CODE"
        )
      )
  }

  @Test
  fun `when adding digital and physical products should generate two shipping`() {

    //given
    val order = Order(customer, Address("Baker Street"))

    val netflix = ProductFactory.createProduct("Product 1", ProductType.MEMBERSHIP, 10.00)
    val book = ProductFactory.createProduct("Product 2", ProductType.BOOK, 5.00)

    order.addProduct(netflix, 1)
    order.addProduct(book, 1)

    //when
    order.pay(CreditCard("43567890-987654367"))

    //then
    val shipping = order.getShipping()

    assertThat(shipping)
      .hasSize(2)
  }

  @Test
  fun `when order payed generate invoice`() {

    //given
    val order = Order(customer, Address("Baker Street"))

    val book = ProductFactory.createProduct("Product 1", ProductType.BOOK, 11.00)
    val shirt = ProductFactory.createProduct("Product 2", ProductType.PHYSICAL, 12.00)

    order.addProduct(shirt, 1)
    order.addProduct(book, 1)
    order.pay(CreditCard("43567890-987654367"))

    //when
    order.invoice()

    //then
    assertThat(order.invoice).isNotNull
    assertThat(order.invoice?.shipping).hasSize(1)
  }
}
