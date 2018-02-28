package nl.uva.se.sc.niro.model

sealed abstract class AnswerType

case object booleanType extends AnswerType
case object integerType extends AnswerType
case object stringType extends AnswerType
case object decimalType extends AnswerType
case object moneyType extends AnswerType
case object dateType extends AnswerType

object AnswerType {
  def apply(answerType: String): AnswerType = answerType match {
    case "boolean" => booleanType
    case "integer" => integerType
    case "string"  => stringType
    case "decimal" => decimalType
    case "money"   => moneyType
    case "date"    => dateType
    case _ =>
      throw new IllegalArgumentException(
        s"Unsupported answer type: $answerType")
  }
}
