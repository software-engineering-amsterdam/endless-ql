package nl.uva.se.sc.niro.gui

import javafx.scene.layout.{HBox, VBox}
import javafx.scene.Parent
import javafx.scene.control.Label

import nl.uva.se.sc.niro.model.Ast._

import scala.collection.JavaConverters

object StatementFactory {

  def createStatements(statements: Seq[Statement]) : java.util.List[Parent] = {
    JavaConverters.seqAsJavaList(statements.map(this.convert))
  }

  def convert(question: Question): Parent = {
    new HBox(new Label(question.label))
  }

  def convert(conditional: Conditional): Parent = {
    val thenPane = new VBox()
    thenPane.managedProperty().bind(thenPane.visibleProperty())
    thenPane.getChildren.addAll(this.createStatements(conditional.ifStatements))

    val elsePane = new VBox()
    elsePane.managedProperty().bind(elsePane.visibleProperty())
    elsePane.getChildren.addAll(this.createStatements(conditional.elseStatements))

    new VBox(thenPane, elsePane)
  }

  def convert(statement: Statement): Parent = {
    statement match {
      case q: Question => convert(q)
      case c: Conditional => convert(c)
    }
  }

}
