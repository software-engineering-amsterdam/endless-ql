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
        case "text" => StringAnswerType
        case "string" => IntAnswerType
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
      sealed trait UnaryExpr extends Operator
      sealed trait ArithmExpr extends Operator
      sealed trait CompExpr extends Operator
      sealed trait LogicalExpr extends Operator

      object UnaryExpr {
        case class Sub(value: Expression) extends UnaryExpr
        case class Not(value: Expression) extends UnaryExpr
      }

      object ArithmExpr {
        case class Sub(left: Expression, right: Expression) extends ArithmExpr
        case class Add(left: Expression, right: Expression) extends ArithmExpr
        case class Div(left: Expression, right: Expression) extends ArithmExpr
        case class Mul(left: Expression, right: Expression) extends ArithmExpr
      }

      object CompExpr {
        case class Lt(left: Expression, right: Expression) extends CompExpr
        case class LTe(left: Expression, right: Expression) extends CompExpr
        case class GTe(left: Expression, right: Expression) extends CompExpr
        case class Gt(left: Expression, right: Expression) extends CompExpr
        case class Ne(left: Expression, right: Expression) extends CompExpr
        case class Eq(left: Expression, right: Expression) extends CompExpr
      }

      object LogicalOp {
        case class Or(left: Expression, right: Expression) extends CompExpr
        case class And(left: Expression, right: Expression) extends CompExpr
        case class Not(left: Expression, right: Expression) extends CompExpr
      }
    }
  }
}
