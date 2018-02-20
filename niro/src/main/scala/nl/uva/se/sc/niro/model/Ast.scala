package nl.uva.se.sc.niro.model

import nl.uva.se.sc.niro.model.Ast.AnswerType.{BooleanAnswerType, DecAnswerType, IntAnswerType, MoneyAnswerType}
import nl.uva.se.sc.niro.model.Ast.Expression.Ident

object Ast {

  case class QLForm(formName: String, statements: Seq[Statement])

  sealed trait Statement

  case class Question(id: Ident, label: String, answerType: AnswerType) extends Statement
  case class Conditional(condition: Expression, ifStatements: Seq[Statement], elseStatements: Seq[Statement]) extends Statement

  sealed trait AnswerType
  object AnswerType {
    case object StringAnswerType extends AnswerType
    case object IntAnswerType extends AnswerType
    case object DecAnswerType extends AnswerType
    case object MoneyAnswerType extends AnswerType
    case object BooleanAnswerType extends AnswerType
    case object DateAnswerType extends AnswerType

    def apply(answerTypeText: String): AnswerType = {
      answerTypeText match {
        case "boolean" => BooleanAnswerType
        case "string" => StringAnswerType
        case "integer" => IntAnswerType
        case "decimal" => DecAnswerType
        case "money" => MoneyAnswerType
        case "date" => DateAnswerType
        case other => throw new IllegalArgumentException(s"Unsupported type: $other")
      }
    }
  }

  sealed trait Expression {
    def getType: AnswerType
  }
  object Expression {
    case class IntConst(value: Int) extends Expression {
      override def getType: AnswerType = IntAnswerType
    }
    case class DecConst(value: Double) extends Expression {
      override def getType: AnswerType = DecAnswerType
    }
    case class BoolConst(value: Boolean) extends Expression {
      override def getType: AnswerType = BooleanAnswerType
    }
    case class Ident(value: String) extends Expression {
      override def getType: AnswerType = ???
    }

    sealed trait Operator extends Expression
    object Operator {
      sealed trait UnaryOp extends Operator
      sealed trait ArithmOp extends Operator
      sealed trait CompOp extends Operator
      sealed trait LogicalOp extends Operator

      object UnaryOp {
        case class Min(value: Expression) extends UnaryOp {
          override def getType: AnswerType = value.getType
        }
        case class Negate(value: Expression) extends UnaryOp {
          override def getType: AnswerType = value.getType
        }

        def apply(operator: String, expression: Expression): UnaryOp = {
          operator match {
            case "-" => Min(expression)
            case "!" => Negate(expression)
            case other => throw new IllegalArgumentException(s"Unsupported unary operator: $other")
          }
        }
      }

      object ArithmOp {
        def getType(left: AnswerType, right: AnswerType): AnswerType = {
          (left, right) match {
            case (MoneyAnswerType, MoneyAnswerType) => MoneyAnswerType
            case (MoneyAnswerType, DecAnswerType) => MoneyAnswerType
            case (MoneyAnswerType, IntAnswerType) => MoneyAnswerType
            case (DecAnswerType, MoneyAnswerType) => MoneyAnswerType
            case (IntAnswerType, MoneyAnswerType) => MoneyAnswerType

            case (DecAnswerType, DecAnswerType) => DecAnswerType
            case (DecAnswerType, IntAnswerType) => DecAnswerType
            case (IntAnswerType, DecAnswerType) => DecAnswerType

            case (IntAnswerType, IntAnswerType) => IntAnswerType

            case (left, right) => throw new IllegalArgumentException(s"Incompatible types for arithmetic operation, left [$left] and right [$right]")
          }
        }
        case class Sub(left: Expression, right: Expression) extends ArithmOp {
          override def getType: AnswerType = ArithmOp.getType(left.getType, right.getType)
        }
        case class Add(left: Expression, right: Expression) extends ArithmOp {
          override def getType: AnswerType = ArithmOp.getType(left.getType, right.getType)
        }
        case class Div(left: Expression, right: Expression) extends ArithmOp {
          override def getType: AnswerType = ArithmOp.getType(left.getType, right.getType)
        }
        case class Mul(left: Expression, right: Expression) extends ArithmOp {
          override def getType: AnswerType = ArithmOp.getType(left.getType, right.getType)
        }

        def apply(operator: String, left: Expression, right: Expression): ArithmOp = {
          operator match {
            case "*" => Mul(left, right)
            case "/" => Div(left, right)
            case "+" => Add(left, right)
            case "-" => Sub(left, right)
            case other => throw new IllegalArgumentException(s"Unsupported arithmetic operator: $other")
          }
        }
      }

      object CompOp {
        case class Lt(left: Expression, right: Expression) extends CompOp {
          override def getType: AnswerType = BooleanAnswerType
        }
        case class LTe(left: Expression, right: Expression) extends CompOp {
          override def getType: AnswerType = BooleanAnswerType
        }
        case class GTe(left: Expression, right: Expression) extends CompOp {
          override def getType: AnswerType = BooleanAnswerType
        }
        case class Gt(left: Expression, right: Expression) extends CompOp {
          override def getType: AnswerType = BooleanAnswerType
        }
        case class Ne(left: Expression, right: Expression) extends CompOp {
          override def getType: AnswerType = BooleanAnswerType
        }
        case class Eq(left: Expression, right: Expression) extends CompOp {
          override def getType: AnswerType = BooleanAnswerType
        }

        def apply(operator: String, left: Expression, right: Expression): CompOp = {
          operator match {
            case "<" => Lt(left, right)
            case "<=" => LTe(left, right)
            case ">=" => GTe(left, right)
            case ">" => Gt(left, right)
            case "!=" => Ne(left, right)
            case "==" => Eq(left, right)
            case other => throw new IllegalArgumentException(s"Unsupported comparison operator: $other")
          }
        }
      }

      object LogicalOp {
        case class Or(left: Expression, right: Expression) extends LogicalOp {
          override def getType: AnswerType = BooleanAnswerType
        }
        case class And(left: Expression, right: Expression) extends LogicalOp {
          override def getType: AnswerType = BooleanAnswerType
        }
        def apply(operator: String, left: Expression, right: Expression): LogicalOp = {
          operator match {
            case "||" => Or(left, right)
            case "&&" => And(left, right)
            case other => throw new IllegalArgumentException(s"Unsupported logical operator: $other")
          }
        }
      }
    }
  }
}
