package nl.uva.se.sc.niro.typechecking.ql

import nl.uva.se.sc.niro.errors.Errors.TypeCheckError
import nl.uva.se.sc.niro.model.ql.expressions.{ Expression, Reference }
import nl.uva.se.sc.niro.model.ql.{ QLForm, Statement }
import org.apache.logging.log4j.scala.Logging

object ReferenceChecker extends Logging {
  def checkReferences(qLForm: QLForm): Either[TypeCheckError, QLForm] = {
    logger.info("Phase 1 - Checking references to undefined questions ...")

    val references: Seq[Reference] = Statement
      .collectAllExpressions(qLForm.statements)
      .flatMap(Expression.collectAllReferences)

    val undefinedReferences: Seq[String] = references.map(_.questionId).filterNot(qLForm.symbolTable.contains)

    if (undefinedReferences.nonEmpty) {
      Left(TypeCheckError(message = s"Undefined references: $undefinedReferences"))
    } else {
      Right(qLForm)
    }
  }
}
