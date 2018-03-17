package nl.uva.se.sc.niro.gui.application

import javafx.application.Application
import javafx.stage.Stage

object QLSForms {
  def main(args: Array[String]): Unit = Application.launch(classOf[QLSForms], args: _*)
}

class QLSForms extends QLForms {
  override def start(stage: Stage): Unit = {
    super.start(stage)
    stage.setTitle("QLS Forms")
  }
}
