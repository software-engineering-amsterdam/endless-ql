package model

object Ast {

  case class QLForm(formName: String, statements: Seq[Statement])

  sealed trait Statement
  import model.Ast.Expression.Ident
  case class Question(id: Ident, label: String, answerType: AnswerType) extends Statement
  case class Conditional(condition: Expression, ifStatements: Seq[Statement], elseStatements: Seq[Statement]) extends Statement

  sealed trait AnswerType
  object AnswerType {
    case object StringAnswerType extends AnswerType
    case object IntAnswerType extends AnswerType
    case object BooleanAnswerType extends AnswerType

    def apply(answerTypeText: String): AnswerType = {
      answerTypeText match {
        case "boolean" => BooleanAnswerType
        case "string" => StringAnswerType
        case "integer" => IntAnswerType
        case other => throw new IllegalArgumentException(s"Unsupported type: $other")
      }
    }
  }

  sealed trait Expression
  object Expression {
    case class IntConst(value: Int) extends Expression
    case class BoolConst(value: Boolean) extends Expression
    case class Ident(value: String) extends Expression

    sealed trait Operator extends Expression
    object Operator {
      sealed trait UnaryOp extends Operator
      sealed trait ArithmOp extends Operator
      sealed trait CompOp extends Operator
      sealed trait LogicalOp extends Operator

      object UnaryOp {
        case class Min(value: Expression) extends UnaryOp
        case class Negate(value: Expression) extends UnaryOp

        def apply(operator: String, expression: Expression): UnaryOp = {
          operator match {
            case "-" => Min(expression)
            case "!" => Negate(expression)
            case other => throw new IllegalArgumentException(s"Unsupported unary operator: $other")
          }
        }
      }

      object ArithmOp {
        case class Sub(left: Expression, right: Expression) extends ArithmOp
        case class Add(left: Expression, right: Expression) extends ArithmOp
        case class Div(left: Expression, right: Expression) extends ArithmOp
        case class Mul(left: Expression, right: Expression) extends ArithmOp

        def apply(operator: String, left: Expression, right: Expression): ArithmOp = {
          operator match {
            case "*" => Mul(left, right)
            case "/" => Div(left, right)
            case "+" => Add(left, right)
            case "-" => Sub(left, right)
            case other => throw new IllegalArgumentException(s"Unsupported arithmatic operator: $other")
          }
        }
      }

      object CompOp {
        case class Lt(left: Expression, right: Expression) extends CompOp
        case class LTe(left: Expression, right: Expression) extends CompOp
        case class GTe(left: Expression, right: Expression) extends CompOp
        case class Gt(left: Expression, right: Expression) extends CompOp
        case class Ne(left: Expression, right: Expression) extends CompOp
        case class Eq(left: Expression, right: Expression) extends CompOp

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
        case class Or(left: Expression, right: Expression) extends LogicalOp
        case class And(left: Expression, right: Expression) extends LogicalOp
        case class Not(left: Expression, right: Expression) extends LogicalOp

        def apply(operator: String, left: Expression, right: Expression): LogicalOp = {
          operator match {
            case "||" => Or(left, right)
            case "&&" => And(left, right)
            case "!=" => Not(left, right)
            case other => throw new IllegalArgumentException(s"Unsupported logical operator: $other")
          }
        }
      }
    }
  }
}
