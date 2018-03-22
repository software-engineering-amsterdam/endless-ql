package nl.uva.se.sc.niro.typechecking.ql

import cats.implicits._
import nl.uva.se.sc.niro.errors.Errors.TypeCheckError
import nl.uva.se.sc.niro.model.ql.expressions.Expression
import nl.uva.se.sc.niro.model.ql.{ AnswerType, QLForm, Statement }
import org.apache.logging.log4j.scala.Logging

object StaticTypeChecker extends Logging {
  def checkOperandsOfInvalidTypeToOperators(qLForm: QLForm): Either[List[TypeCheckError], QLForm] = {
    logger.info("Phase 3 - Checking operands of invalid type to operators ...")

    val conditionals = Statement.collectAllConditionals(qLForm.statements)
    val expressions: Iterable[Expression] = qLForm.symbolTable.values.flatMap(_.expression)
    val predicates = conditionals.map(_.predicate)

    (expressions ++ predicates)
      .map(expression => expression.typeOf(qLForm.symbolTable))
      .map(either => either.toValidatedNel)
      .toList
      .sequenceU_
      .as(AnswerType)
      .toEither
      .left
      .map(_.toList)
      .right
      .map(_ => qLForm)
  }
}
