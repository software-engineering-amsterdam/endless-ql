package nl.uva.se.sc.niro.gui

import javafx.scene.layout._
import nl.uva.se.sc.niro.model.Expressions.Expression
import nl.uva.se.sc.niro.model._

object StatementVisitor {

  def visit(grid: GridPane, statements: Seq[Statement], symbolTable: Map[String, Expression]): Unit = {
    grid.setHgap(10)

    statements.zipWithIndex foreach {
      case (statement, row) => statement match {
        case question: Question => {
          RowBuilder.buildDoubleColumnRow(grid, row)
          WidgetFactory.makeWidgets(question, symbolTable).zipWithIndex foreach {
            case (widget, column) => grid.add(widget, column, row)
          }
        }
        case conditional: Conditional => {
          val innerGrid = RowBuilder.buildSingleColumnRow(grid, row)
          // FIXME and make dynamic!
//          innerGrid.setVisible(conditional.predicate.value)
          visit(innerGrid, conditional.thenStatements, symbolTable)
        }
        case ErrorStatement() => ()
      }
    }
  }

}
