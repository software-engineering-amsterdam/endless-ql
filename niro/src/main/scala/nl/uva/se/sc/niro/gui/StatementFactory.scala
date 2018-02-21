package nl.uva.se.sc.niro.gui

import java.lang
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javafx.beans.value.{ChangeListener, ObservableValue}
import javafx.scene.Parent
import javafx.scene.control.{CheckBox, DatePicker, Label, TextField}
import javafx.scene.layout._
import javafx.util.StringConverter

import nl.uva.se.sc.niro.model.Ast._
import nl.uva.se.sc.niro.model.Expressions.Expression
import nl.uva.se.sc.niro.model.Expressions.Expression.Answer
import nl.uva.se.sc.niro.model.Expressions.answers._

object StatementFactory {

  def createStatements(gridPane: GridPane, statements: Seq[Statement]): Unit = {
    gridPane.setHgap(10)

    var rowNr = 0
    for (statement <- statements) {
      statement match {
        case question: Question => {
          gridPane.getRowConstraints.add(new RowConstraints())
          gridPane.add(new Label(question.label), 0, rowNr)
          gridPane.add(convert(Expression.evaluate(question.answer)), 1, rowNr)
        }
        case condition: Conditional => {
          val thenConstraint = new RowConstraints()
          gridPane.getRowConstraints.add(thenConstraint)

          val thenPane = new GridPane()
          gridPane.add(thenPane, 0, rowNr, 2, 1)
          // When invisible we don't occupy any space
          thenPane.managedProperty().bind(thenPane.visibleProperty())
          bindConstraintToVisiblity(thenConstraint, thenPane)
          createStatements(thenPane, condition.ifStatements)

          if (!condition.elseStatements.isEmpty) {
            rowNr += 1

            val elseConstraint = new RowConstraints()
            gridPane.getRowConstraints.add(elseConstraint)

            // When invisible we don't occupy any space
            val elsePane = new GridPane()
            gridPane.add(elsePane, 0, rowNr, 2, 1)
            elsePane.managedProperty().bind(elsePane.visibleProperty())
            bindConstraintToVisiblity(elseConstraint, elsePane)
            // Exclusive visibility with thenPane
            elsePane.visibleProperty().bind(thenPane.visibleProperty().not())

            createStatements(elsePane, condition.elseStatements)
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

  private def bindConstraintToVisiblity(constraint: RowConstraints, pane: GridPane) = {
    pane.visibleProperty().addListener(new ChangeListener[lang.Boolean] {
      override def changed(observable: ObservableValue[_ <: lang.Boolean], oldValue: lang.Boolean, newValue: lang.Boolean): Unit = {
        constraint.setPrefHeight(if(newValue) { -1.0} else {0.0})
      }
    })
  }

}
