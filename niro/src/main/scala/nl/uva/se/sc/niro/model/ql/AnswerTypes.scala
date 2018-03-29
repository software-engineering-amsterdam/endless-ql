package nl.uva.se.sc.niro.model.ql

sealed trait AnswerType {
  def isCompatibleWith(that: AnswerType): Boolean = {
    this == that
  }
}

case object BooleanType extends AnswerType
case object DateType extends AnswerType
case object NumericType extends AnswerType {
  override def isCompatibleWith(that: AnswerType): Boolean = that match {
    case NumericType => true
    case IntegerType => true
    case DecimalType => true
    case MoneyType   => true
    case _           => false
  }
}
case object DecimalType extends AnswerType {
  override def isCompatibleWith(that: AnswerType): Boolean = that match {
    case NumericType => true
    case IntegerType => true
    case DecimalType => true
    case MoneyType   => true
    case _           => false
  }
}

case object IntegerType extends AnswerType {
  override def isCompatibleWith(that: AnswerType): Boolean = that match {
    case NumericType => true
    case IntegerType => true
    case DecimalType => true
    case MoneyType   => true
    case _           => false
  }
}

case object MoneyType extends AnswerType {
  override def isCompatibleWith(that: AnswerType): Boolean = that match {
    case NumericType => true
    case IntegerType => true
    case DecimalType => true
    case MoneyType   => true
    case _           => false
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
