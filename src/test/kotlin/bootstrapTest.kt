import com.sacredit.customer.Address
import com.sacredit.customer.Customer
import com.sacredit.order.Order
import com.sacredit.product.ProductFactory
import com.sacredit.product.ProductType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


class BootstrapTest {

    @Test
    fun `when adding product to order`() {
        val order = Order(Customer(), Address())
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
        val order = Order(Customer(), Address())
        val music1 = ProductFactory.createProduct("Product 1", ProductType.DIGITAL, 10.00)
        val book = ProductFactory.createProduct("Product 2", ProductType.BOOK, 15.00)


        order.addProduct(music1, 2)
        order.addProduct(book, 2)

        assertThat(order.totalAmount).isEqualTo(50.0)
    }
}
