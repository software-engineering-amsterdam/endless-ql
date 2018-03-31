package nl.uva.se.sc.niro.model.ql

import nl.uva.se.sc.niro.errors.Warning
import nl.uva.se.sc.niro.model.ql.SymbolTable.SymbolTable

case class QLForm(formName: String, statements: Seq[Statement], warnings: Seq[Warning] = Seq.empty) {
  val symbolTable: SymbolTable = SymbolTable.createSymbolTable(this)
}
