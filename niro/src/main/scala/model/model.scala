package object model {

  case class QLForm(formName: String, statements: List[Statement])

  sealed trait Statement
  case class Question(id: String, label: String, answerType: AnswerType) extends Statement

  sealed trait AnswerType
  case class StringAnswerType(value: String) extends AnswerType
  case class IntAnswerType(value: Int) extends AnswerType
  case class BooleanAnswerType(value: Boolean) extends AnswerType

}
