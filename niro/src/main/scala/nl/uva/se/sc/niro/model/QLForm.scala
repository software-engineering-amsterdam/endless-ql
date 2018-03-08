package nl.uva.se.sc.niro.model

case class QLForm(formName: String, statements: Seq[Statement]) {
  val symbolTable: Map[String, Symbol] = SymbolTable.createSymbolTable(this)
}
