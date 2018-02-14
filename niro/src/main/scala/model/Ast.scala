package model

object Ast {

  sealed class Node()
  case class QLForm(formName: String, statements: Seq[Statement]) extends Node

  sealed trait Statement
  case class Question(id: String, label: String, answerType: AnswerType) extends Node with Statement

  sealed trait AnswerType
  object AnswerType extends Node {
    case object StringAnswerType extends AnswerType
    case object IntAnswerType extends AnswerType
    case object BooleanAnswerType extends AnswerType

    def apply(answerTypeText: String): AnswerType = {
      answerTypeText match {
        case "boolean" => BooleanAnswerType
        case "text" => StringAnswerType
        case "string" => IntAnswerType
        case other => throw new IllegalArgumentException(s"Unsupported type: $other")
      }
    }
  }
}
