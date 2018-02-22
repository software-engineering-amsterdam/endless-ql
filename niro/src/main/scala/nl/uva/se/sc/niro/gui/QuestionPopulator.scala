package nl.uva.se.sc.niro.gui

import java.lang

import javafx.beans.value.{ ChangeListener, ObservableValue }
import javafx.scene.control.Label
import javafx.scene.layout._
import nl.uva.se.sc.niro.Evaluator._
import nl.uva.se.sc.niro.model._

object QuestionPopulator {

  def populateGridWithQuestions(grid: GridPane, statements: Seq[Statement]): Unit = {
    grid.setHgap(10)

    var rowNr = 0
    for (statement <- statements) {
      statement match {
        case question: Question => {
          grid.getRowConstraints.add(new RowConstraints())
          grid.addRow(rowNr, new Label(question.label), WidgetFactory.makeWidget(evaluateExpression(question.answer, Map.empty)))
        }
        case condition: Conditional => {
          val thenPane = insertQuestionPaneAtRow(grid, rowNr)
          populateGridWithQuestions(thenPane, condition.ifStatements)

          if (!condition.elseStatements.isEmpty) {
            rowNr += 1
            val elsePane = insertQuestionPaneAtRow(grid, rowNr)
            populateGridWithQuestions(elsePane, condition.elseStatements)
            // Exclusive visibility with thenPane
            elsePane.visibleProperty().bind(thenPane.visibleProperty().not())
          }
        }
      }
      rowNr += 1
    }
  }

  private def insertQuestionPaneAtRow(grid: GridPane, rowNr: Int): GridPane = {
    val thenConstraint = new RowConstraints()
    grid.getRowConstraints.add(thenConstraint)

    val innerGrid = new GridPane()
    grid.add(innerGrid, 0, rowNr, 2, 1)
    // When invisible we don't occupy any space
    innerGrid.managedProperty().bind(innerGrid.visibleProperty())
    bindConstraintToVisiblity(thenConstraint, innerGrid)

    innerGrid
  }

  private def bindConstraintToVisiblity(constraint: RowConstraints, pane: GridPane) = {
    pane.visibleProperty().addListener(new ChangeListener[lang.Boolean] {
      override def changed(observable: ObservableValue[_ <: lang.Boolean], oldValue: lang.Boolean, newValue: lang.Boolean): Unit = {
        constraint.setPrefHeight(if(newValue) { -1.0} else {0.0})
      }
    })
  }

}
