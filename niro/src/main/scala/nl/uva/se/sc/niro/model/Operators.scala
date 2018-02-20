package nl.uva.se.sc.niro.model

object Operators {
  abstract class Operator
  abstract class BinaryOperator extends Operator
  object BinaryOperator {
    def apply(operator: String): BinaryOperator = operator match {
      case "<" => Lt
      case "<=" => LTe
      case "==" => Eq
      case "!=" => Ne
      case ">=" => GTe
      case ">" => Gt
      case "-" => Sub
      case "+" => Add
      case "/" => Div
      case "*" => Mul
      case "||" => Or
      case "&&" => And
    }
  }

  abstract class UnaryOperator extends Operator
  object UnaryOperator {
    def apply(operator: String): UnaryOperator = operator match {
      case "!" => Neg
      case "-" => Min
    }
  }


  abstract class BasicArithmeticOperator extends BinaryOperator
  case object Add extends BasicArithmeticOperator
  case object Sub extends BasicArithmeticOperator
  case object Mul extends BasicArithmeticOperator
  case object Div extends BasicArithmeticOperator

  abstract class ComparisonOperator extends BinaryOperator
  case object Lt extends ComparisonOperator
  case object LTe extends ComparisonOperator
  case object GTe extends ComparisonOperator
  case object Gt extends ComparisonOperator
  case object Ne extends ComparisonOperator
  case object Eq extends ComparisonOperator

  abstract class LogicalOperator extends BinaryOperator
  case object Or extends LogicalOperator
  case object And extends LogicalOperator

  case object Min extends UnaryOperator
  case object Neg extends UnaryOperator
}
