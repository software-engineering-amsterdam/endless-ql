package nl.uva.se.sc.niro.gui

import javafx.scene.layout.{HBox, Pane, VBox}
import javafx.scene.Parent
import javafx.scene.control.Label

import nl.uva.se.sc.niro.model.Ast._

import scala.collection.JavaConverters

object StatementFactory {
  def createStatements(statements: Seq[Statement]) : java.util.List[Parent] = {
    println(statements.size)
    JavaConverters.seqAsJavaList(statements.map(this.convert))
  }

  def convert(question: Question): Parent = {
    println("question")
    new HBox(new Label("Question"))
  }
  def convert(conditional: Conditional): Parent = {
    println("conditional")
    val thenPane = new Pane()
    thenPane.getChildren.addAll(this.createStatements(conditional.ifStatements))
    val elsePane = new Pane()
    elsePane.getChildren.addAll(this.createStatements(conditional.elseStatements))
    new VBox(thenPane, elsePane)
  }

  def convert(statement: Statement): Parent = {
    println("statement")
    new HBox(new Label("Statement"))
  }

}
