package nl.uva.se.sc.niro.model.ql.expressions

import nl.uva.se.sc.niro.model.ql.Operators._

trait Expression
trait UnaryExpression extends Expression {
  val right: Expression
}
trait BinaryExpression extends Expression {
  val left: Expression
  val right: Expression
}
final case class Reference(questionId: String) extends Expression
final case class Addition(left: Expression, right: Expression) extends BinaryExpression
final case class Subtract(left: Expression, right: Expression) extends BinaryExpression
final case class Multiply(left: Expression, right: Expression) extends BinaryExpression
final case class Divide(left: Expression, right: Expression) extends BinaryExpression
final case class Minus(right: Expression) extends UnaryExpression
final case class LessThan(left: Expression, right: Expression) extends BinaryExpression
final case class LessThanEqual(left: Expression, right: Expression) extends BinaryExpression
final case class GreaterThenEqual(left: Expression, right: Expression) extends BinaryExpression
final case class GreaterThen(left: Expression, right: Expression) extends BinaryExpression
final case class NotEqual(left: Expression, right: Expression) extends BinaryExpression
final case class Equal(left: Expression, right: Expression) extends BinaryExpression
final case class Or(left: Expression, right: Expression) extends BinaryExpression
final case class And(left: Expression, right: Expression) extends BinaryExpression
final case class Negate(right: Expression) extends UnaryExpression

object Expression {
  def apply(operator: String, left: Expression): Expression = operator match {
    case "-" => Minus(left)
    case "!" => Negate(left)
    case _   => throw new IllegalArgumentException(s"Unknown operator $operator")
  }

  def apply(operator: String, left: Expression, right: Expression): Expression = operator match {
    case "<"  => LessThan(left, right)
    case "<=" => LessThanEqual(left, right)
    case "==" => Equal(left, right)
    case "!=" => NotEqual(left, right)
    case ">=" => GreaterThenEqual(left, right)
    case ">"  => GreaterThen(left, right)
    case "-"  => Subtract(left, right)
    case "+"  => Addition(left, right)
    case "/"  => Divide(left, right)
    case "*"  => Multiply(left, right)
    case "||" => Or(left, right)
    case "&&" => And(left, right)
    case _    => throw new IllegalArgumentException(s"Unknown operator $operator")
  }

  def collectAllReferences(expression: Expression): Seq[Reference] = expression match {
    case r: Reference        => Seq(r)
    case u: UnaryExpression  => collectAllReferences(u.right)
    case b: BinaryExpression => collectAllReferences(b.left) ++ collectAllReferences(b.right)
    case _                   => Seq.empty
  }
}
