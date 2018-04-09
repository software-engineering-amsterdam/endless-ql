import scala.io.Source
import java.io.File

import ql.ui._
import ql.app._

object Main extends App {
  override def main(args: Array[String]) = {

    val fileLocation = new File(args(0)).toURI().toURL();
    val app = new QLApp(fileLocation)
    app.main(args)
  }
}
