package nl.uva.se.sc.niro.gui

import java.lang
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javafx.beans.value.{ChangeListener, ObservableValue}
import javafx.geometry.Insets
import javafx.scene.Parent
import javafx.scene.control.{CheckBox, DatePicker, Label, TextField}
import javafx.scene.layout._
import javafx.util.StringConverter

import nl.uva.se.sc.niro.model.Ast._
import nl.uva.se.sc.niro.model.Expressions.Expression
import nl.uva.se.sc.niro.model.Expressions.Expression.Answer
import nl.uva.se.sc.niro.model.Expressions.answers._

object StatementFactory {

  def createStatements(grid: GridPane, statements: Seq[Statement]): Unit = {
    grid.setHgap(10)

    var rowNr = 0
    for (statement <- statements) {
      statement match {
        case question: Question => {
          grid.getRowConstraints.add(new RowConstraints())
          grid.addRow(rowNr, new Label(question.label), convert(Expression.evaluate(question.answer)))
        }
        case condition: Conditional => {
          val thenPane = insertQuestionPaneAtRow(grid, rowNr, condition.ifStatements)

          if (!condition.elseStatements.isEmpty) {
            rowNr += 1
            val elsePane = insertQuestionPaneAtRow(grid, rowNr, condition.elseStatements)
            // Exclusive visibility with thenPane
            elsePane.visibleProperty().bind(thenPane.visibleProperty().not())
          }
        }
      }
      rowNr += 1
    }
  }

  def convert(answer: Answer): Parent = {
    answer match {
      case _: BooleanAnswer => new CheckBox()
      case _: StringAnswer => new TextField()
      case _: IntAnswer => createIntegerField()
      case _: DecAnswer => createDecimalField()
      case _: MoneyAnswer => createDecimalField()
      case _: DateAnswer => createDateField
      case other => new Label(s"Unimplemented type: $other")
    }
  }

  private def createIntegerField(): Parent = {
    createRegExField("\\d+")
  }

  private def createDecimalField(): Parent = {
    createRegExField("\\d*(,\\d{0,2})?")
  }

  private def createRegExField(validPattern: String): Parent = {
    val regexField = new TextField()
    regexField.textProperty().addListener(new ChangeListener[String] {
      override def changed(observable: ObservableValue[_ <: String], oldValue: String, newValue: String): Unit = {
        if (!newValue.matches(validPattern)) {
          regexField.setText(oldValue)
        }
      }
    })
    regexField
  }

  private def createDateField: Parent = {
    val dateField = new DatePicker()
    dateField.setConverter(new StringConverter[LocalDate] {
      val dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy")

      override def toString(date: LocalDate): String = {
        if (date != null) dateFormat.format(date) else null
      }

      override def fromString(string: String): LocalDate = {
        if (string != null && !string.isEmpty) LocalDate.parse(string, dateFormat) else null
      }
    })
    dateField
  }

  private def insertQuestionPaneAtRow(grid: GridPane, rowNr: Int, statements: Seq[Statement]): GridPane = {
    val thenConstraint = new RowConstraints()
    grid.getRowConstraints.add(thenConstraint)

    val innerGrid = new GridPane()
    grid.add(innerGrid, 0, rowNr, 2, 1)
    // When invisible we don't occupy any space
    innerGrid.managedProperty().bind(innerGrid.visibleProperty())
    bindConstraintToVisiblity(thenConstraint, innerGrid)

    createStatements(innerGrid, statements)
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
