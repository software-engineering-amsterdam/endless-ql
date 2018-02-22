package nl.uva.se.sc.niro.gui

import java.lang

import javafx.beans.value.{ ChangeListener, ObservableValue }
import javafx.scene.control.Label
import javafx.scene.layout._
import nl.uva.se.sc.niro.Evaluator._
import nl.uva.se.sc.niro.model.Expressions.Expression
import nl.uva.se.sc.niro.model._

object QuestionPopulator {

  def populateGridWithQuestions(grid: GridPane, statements: Seq[Statement], symbolTable: Map[String, Expression]): Unit = {
    grid.setHgap(10)

    statements.zipWithIndex foreach {
      case (statement, row) => statement match {
        case question: Question => populateQuestionAtRow(grid, row, question, symbolTable)
        case conditional: Conditional => populateConditionAtRow(grid, row, conditional, symbolTable)
      }
    }
  }

  private def populateQuestionAtRow(grid: GridPane, rowNr: Int, question: Question, symbolTable: Map[String, Expression]): Unit = {
    grid.getRowConstraints.add(new RowConstraints())
    grid.addRow(rowNr, new Label(question.label), WidgetFactory.makeWidget(evaluateExpression(question.answer, symbolTable)))
  }

  private def populateConditionAtRow(grid: GridPane, rowNr: Int, conditional: Conditional, symbolTable: Map[String, Expression]): Unit = {
    val innerPane = createQuestionPaneAtRow (grid, rowNr)
    populateGridWithQuestions(innerPane, conditional.thenStatements, symbolTable)
  }

  private def createQuestionPaneAtRow(grid: GridPane, rowNr: Int): GridPane = {
    // A row constraint is needed for hiding and showing entire rows.
    val rowConstraint = new RowConstraints()
    grid.getRowConstraints.add(rowConstraint)

    val innerGrid = new GridPane()
    grid.add(innerGrid, 0, rowNr, 2, 1)
    // When invisible we don't occupy any space
    innerGrid.managedProperty().bind(innerGrid.visibleProperty())
    bindConstraintToVisiblity(rowConstraint, innerGrid)

    innerGrid
  }

  /**
    * By setting the preferred height to 0.0 the entire row is hidden, and by setting it to -1.0 the layout manager will
    * recalculate the height of the row and make is visible again.
    */
  private def bindConstraintToVisiblity(constraint: RowConstraints, pane: GridPane) = {
    pane.visibleProperty().addListener(new ChangeListener[lang.Boolean] {
      override def changed(observable: ObservableValue[_ <: lang.Boolean], oldValue: lang.Boolean, newValue: lang.Boolean): Unit = {
        constraint.setPrefHeight(if(newValue) { -1.0} else {0.0})
      }
    })
  }

}
