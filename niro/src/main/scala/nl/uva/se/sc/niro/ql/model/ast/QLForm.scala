package nl.uva.se.sc.niro.ql.model.ast

import nl.uva.se.sc.niro.errors.Warning
import nl.uva.se.sc.niro.ql.model.ast.SymbolTable.SymbolTable

case class QLForm(formName: String, statements: Seq[Statement], warnings: Seq[Warning] = Seq.empty) {
  val symbolTable: SymbolTable = SymbolTable.createSymbolTable(this)
}
