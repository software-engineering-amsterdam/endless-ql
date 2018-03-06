package nl.uva.se.sc.niro.parser

import org.antlr.v4.runtime.RecognitionException

class SyntaxErrorInfo(
    line: Int,
    column: Int,
    offendingSymbol: String,
    errorMessage: String,
    rules: List[String],
    cause: RecognitionException)
    extends ParseErrorInfo {
  override def toString: String =
    String.format(s"At line ${line}, column ${column} the following error occurred : ${errorMessage}")
}
