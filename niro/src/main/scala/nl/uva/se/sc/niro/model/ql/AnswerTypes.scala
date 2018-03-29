package nl.uva.se.sc.niro.model.ql

sealed trait AnswerType {
  def getWidest(that: AnswerType): Option[AnswerType] = {
    if (this == that) Some(this) else None
  }
}

case object BooleanType extends AnswerType
case object DateType extends AnswerType
case object DecimalType extends AnswerType {
  override def getWidest(that: AnswerType): Option[AnswerType] = that match {
    case IntegerType => Some(DecimalType)
    case DecimalType => Some(DecimalType)
    case MoneyType   => Some(MoneyType)
    case _           => None
  }
}

case object IntegerType extends AnswerType {
  override def getWidest(that: AnswerType): Option[AnswerType] = that match {
    case IntegerType => Some(IntegerType)
    case DecimalType => Some(DecimalType)
    case MoneyType   => Some(MoneyType)
    case _           => None
  }
}

case object MoneyType extends AnswerType {
  override def getWidest(that: AnswerType): Option[AnswerType] = that match {
    case IntegerType => Some(MoneyType)
    case DecimalType => Some(MoneyType)
    case MoneyType   => Some(MoneyType)
    case _           => None
  }
}

case object StringType extends AnswerType

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
