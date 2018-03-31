package nl.uva.se.sc.niro.typechecking.ql

import cats.implicits._
import nl.uva.se.sc.niro.errors.Errors.TypeCheckError
import nl.uva.se.sc.niro.ql.model.ast.expressions.{ Expression, Reference }
import nl.uva.se.sc.niro.ql.model.ast.{ QLForm, Statement }
import org.apache.logging.log4j.scala.Logging

object References extends Logging {
  def check(qLForm: QLForm): Either[Seq[TypeCheckError], QLForm] = {
    logger.info("Phase 1 - Checking references to undefined questions ...")

    val references: Seq[Reference] = Statement
      .collectAllExpressions(qLForm.statements)
      .flatMap(Expression.collectAllReferences)

    val undefinedReferences: Seq[String] = references.map(_.questionId).filterNot(qLForm.symbolTable.contains)

    if (undefinedReferences.nonEmpty) {
      undefinedReferences
        .map(undefinedReference => TypeCheckError(message = s"Undefined reference: $undefinedReference"))
        .asLeft
    } else {
      qLForm.asRight
    }
  }
}
