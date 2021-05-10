import com.sacredit.screen.EnterStoreScreen
import com.sacredit.screen.Screen
import java.util.*

fun main(args: Array<String>) {

  val scanner = Scanner(System.`in`)
  var currentScreen: Screen = EnterStoreScreen()
  currentScreen.init(null)

  while (scanner.hasNext()) {
    val oldScreen = currentScreen
    currentScreen = currentScreen.executeInput(scanner.next())
    currentScreen.init(oldScreen)
  }
}
