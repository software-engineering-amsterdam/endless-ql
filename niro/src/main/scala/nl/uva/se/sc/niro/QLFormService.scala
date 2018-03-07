package nl.uva.se.sc.niro

import java.io.File

import nl.uva.se.sc.niro.model.QLForm
import nl.uva.se.sc.niro.parser.QLFormParser
import org.antlr.v4.runtime.CharStreams
import nl.uva.se.sc.niro.errors.Errors._
import nl.uva.se.sc.niro.typechecking.TypeChecker

import scala.util.{ Failure, Success, Try }

object QLFormService {

  def importQLSpecification(file: File): Either[QLForm, Seq[Error]] = {
    val qlFormAst: QLForm = QLFormParser.parse(CharStreams.fromFileName(file.getAbsolutePath))
    val parseErrors: Seq[Error] = QLFormParser.getParseErrors.toList

    if (parseErrors.isEmpty) {
      Try(TypeChecker.pipeline(qlFormAst)) match {
        case Success(qLForm: QLForm) => Left(qLForm)
        case Failure(ex)             => Right(Seq(UnknownError("TypeCheckError", ex.getMessage))): Either[QLForm, Seq[Error]]
      }
    } else {
      Right(parseErrors)
    }
  }
}
