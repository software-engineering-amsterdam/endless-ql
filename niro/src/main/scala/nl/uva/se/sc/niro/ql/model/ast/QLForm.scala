package nl.uva.se.sc.niro.ql.model.ast

import nl.uva.se.sc.niro.errors.Warning

case class QLForm(formName: String, statements: Seq[Statement], warnings: Seq[Warning] = Seq.empty) {
  val symbolTable: Map[String, Symbol] = SymbolTable.createSymbolTable(this)
}
