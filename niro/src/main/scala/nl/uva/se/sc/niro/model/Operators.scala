package nl.uva.se.sc.niro.model

sealed abstract class BinaryOperator
object BinaryOperator {
  def apply(operator: String): BinaryOperator = operator match {
    case "<" => Lt
    case "<=" => Lte
    case "==" => Eq
    case "!=" => Ne
    case ">=" => Gte
    case ">" => Gt
    case "-" => Sub
    case "+" => Add
    case "/" => Div
    case "*" => Mul
    case "||" => Or
    case "&&" => And
  }
}

sealed abstract class UnaryOperator
object UnaryOperator {
  def apply(operator: String): UnaryOperator = operator match {
    case "!" => Neg
    case "-" => Min
  }
}

case object Add extends BinaryOperator
case object Sub extends BinaryOperator
case object Mul extends BinaryOperator
case object Div extends BinaryOperator

case object Lt extends BinaryOperator
case object Lte extends BinaryOperator
case object Gte extends BinaryOperator
case object Gt extends BinaryOperator
case object Ne extends BinaryOperator
case object Eq extends BinaryOperator

case object Or extends BinaryOperator
case object And extends BinaryOperator

case object Min extends UnaryOperator
case object Neg extends UnaryOperator
