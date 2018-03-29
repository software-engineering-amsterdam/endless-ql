package nl.uva.se.sc.niro.errors

import org.antlr.v4.runtime.RecognitionException

object Errors {

  trait Error {
    val key: String
    val message: String
  }

  case class SyntaxErrorInfo(
      line: Int,
      column: Int,
      offendingSymbol: String,
      errorMessage: String,
      cause: RecognitionException)
      extends Error {

    val key = "Syntax Error"
    val message =  s"At line $line, column $column the following error occurred : $errorMessage"
  }

  case class TypeCheckError(key: String = "TypeCheckError", message: String) extends Error
}
