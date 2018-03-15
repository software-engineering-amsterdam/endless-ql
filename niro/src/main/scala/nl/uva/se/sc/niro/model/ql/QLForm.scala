package nl.uva.se.sc.niro.model.ql

import nl.uva.se.sc.niro.errors.Warning

case class QLForm(formName: String, statements: Seq[Statement], warnings: Seq[Warning] = Seq.empty) {
  val symbolTable: Map[String, Symbol] = SymbolTable.createSymbolTable(this)
}
