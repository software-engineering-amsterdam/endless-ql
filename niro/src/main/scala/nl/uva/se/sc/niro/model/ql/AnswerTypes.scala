package nl.uva.se.sc.niro.model.ql

import cats.implicits._
import nl.uva.se.sc.niro.errors.Errors.TypeCheckError

sealed trait AnswerType {
  def typeOf(operator: Operator): Either[TypeCheckError, AnswerType]

  def isCompatibleWith(that: AnswerType): Either[TypeCheckError, AnswerType] = {
    if (this == that) {
      this.asRight
    } else TypeCheckError(message = s"Operands of invalid type: $this, $that").asLeft
  }
}

case object BooleanType extends AnswerType {
  def typeOf(operator: Operator): Either[TypeCheckError, AnswerType] = operator match {
    case _: BooleanOperator => operator.typeOf(this).asRight
    case _: LogicalOperator => operator.typeOf(this).asRight
    case _                  => TypeCheckError(message = s"Operand: $BooleanType of invalid type to operator: $operator").asLeft
  }
}

case object DateType extends AnswerType {
  def typeOf(operator: Operator): Either[TypeCheckError, AnswerType] = operator match {
    case _: ArithmeticOperator => operator.typeOf(this).asRight
    case _: BooleanOperator    => operator.typeOf(this).asRight
    case _                     => TypeCheckError(message = s"Operand: $DateType of invalid type to operator: $operator").asLeft
  }
}

case object DecimalType extends AnswerType {
  def typeOf(operator: Operator): Either[TypeCheckError, AnswerType] = operator match {
    case _: ArithmeticOperator => operator.typeOf(this).asRight
    case _: BooleanOperator    => operator.typeOf(this).asRight
    case _                     => TypeCheckError(message = s"Operand: $DecimalType of invalid type to operator: $operator").asLeft
  }
}

case object IntegerType extends AnswerType {
  def typeOf(operator: Operator): Either[TypeCheckError, AnswerType] = operator match {
    case _: ArithmeticOperator => operator.typeOf(this).asRight
    case _: BooleanOperator    => operator.typeOf(this).asRight
    case _                     => TypeCheckError(message = s"Operand: $IntegerType of invalid type to operator: $operator").asLeft
  }
}

case object MoneyType extends AnswerType {
  def typeOf(operator: Operator): Either[TypeCheckError, AnswerType] = operator match {
    case _: ArithmeticOperator => operator.typeOf(this).asRight
    case _: BooleanOperator    => operator.typeOf(this).asRight
    case _                     => TypeCheckError(message = s"Operand: $MoneyType of invalid type to operator: $operator").asLeft
  }
}

case object StringType extends AnswerType {
  def typeOf(operator: Operator): Either[TypeCheckError, AnswerType] = operator match {
    case _: BooleanOperator => operator.typeOf(this).asRight
    case _                  => TypeCheckError(message = s"Operand: $StringType of invalid type to operator: $operator").asLeft
  }
}

object AnswerType {
  def apply(answerType: String): AnswerType = answerType match {
    case "boolean" => BooleanType
    case "integer" => IntegerType
    case "string"  => StringType
    case "decimal" => DecimalType
    case "money"   => MoneyType
    case "date"    => DateType
    case _ =>
      throw new IllegalArgumentException(s"Unsupported answer type: $answerType")
  }
}
