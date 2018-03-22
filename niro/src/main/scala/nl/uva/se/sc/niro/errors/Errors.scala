package nl.uva.se.sc.niro.errors

import org.antlr.v4.runtime.RecognitionException

object Errors {

  trait Error

  case class UnknownError(key: String = "Unknown Error", message: String) extends Error

  case class AmbiguityErrorInfo(key: String = "Ambiguity Error", message: String) extends Error

  case class ContextSensitivityErrorInfo(key: String = "Context Sensitivity Error", message: String) extends Error

  case class AttemptingFullContextErrorInfo(key: String = "Attempting Full Context Error", message: String)
      extends Error

  case class SyntaxErrorInfo(
      line: Int,
      column: Int,
      offendingSymbol: String,
      errorMessage: String,
      rules: List[String],
      cause: RecognitionException)
      extends Error {

    val key = "Syntax Error"
    val message = "Syntax Error"

    override def toString: String =
      String.format(s"At line $line, column $column the following error occurred : $errorMessage")
  }

  case class TypeCheckError(key: String = "TypeCheckError", message: String) extends Error
}
