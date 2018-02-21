package nl.uva.se.sc.niro.gui

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javafx.beans.value.{ChangeListener, ObservableValue}
import javafx.scene.Parent
import javafx.scene.control.{ CheckBox, DatePicker, Label, TextField }
import javafx.scene.layout._
import javafx.util.StringConverter

import nl.uva.se.sc.niro.model.Ast._
import nl.uva.se.sc.niro.model.Expressions.Expression
import nl.uva.se.sc.niro.model.Expressions.Expression.Answer
import nl.uva.se.sc.niro.model.Expressions.answers._

object StatementFactory {

  def createStatements(gridPane: GridPane, statements: Seq[Statement]): Unit = {
    gridPane.setHgap(10)
    gridPane.setGridLinesVisible(true)

    var rowNr = 0
    for (statement <- statements) {
      statement match {
        case question: Question => {
          gridPane.add(new Label(question.label), 0, rowNr)
          gridPane.add(convert(Expression.evaluate(question.answer)), 1, rowNr)
          gridPane.getRowConstraints.add(new RowConstraints())
        }
        case condition: Conditional => {
          val thenPane = new GridPane()
          // When invisible we don't occupy any space
          thenPane.managedProperty().bind(thenPane.visibleProperty())
          gridPane.add(thenPane, 0, rowNr, 2, 1)
          gridPane.getRowConstraints.add(new RowConstraints())
          createStatements(thenPane, condition.ifStatements)

          if (!condition.elseStatements.isEmpty) {
            rowNr += 1
            val constraint = new RowConstraints()
            gridPane.getRowConstraints.add(constraint)

            // When invisible we don't occupy any space
            val elsePane = new GridPane()
            elsePane.managedProperty().bind(thenPane.visibleProperty())
            // Exclusive visibility with thenPane
            elsePane.visibleProperty().bind(thenPane.visibleProperty().not())
            gridPane.add(elsePane, 0, rowNr, 2, 1)
            createStatements(elsePane, condition.elseStatements)
            constraint.prefHeightProperty().set(0)
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

}
