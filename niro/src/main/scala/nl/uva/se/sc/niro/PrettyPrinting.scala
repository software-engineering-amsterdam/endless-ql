package nl.uva.se.sc.niro

import nl.uva.se.sc.niro.model.ql.expressions._
import nl.uva.se.sc.niro.model.ql.expressions.answers._

trait PrettyPrintable[T] {
  def prettyPrint: String
}

object PrettyPrinter {
  implicit class ExpressionCanPrettyPrint(expression: Expression) extends PrettyPrintable[Expression] {
    override def prettyPrint: String = expression match {
      case r: Reference        => r.prettyPrint
      case a: Addition         => a.prettyPrint
      case s: Subtract         => s.prettyPrint
      case m: Multiply         => m.prettyPrint
      case d: Divide           => d.prettyPrint
      case m: Minus            => m.prettyPrint
      case l: LessThan         => l.prettyPrint
      case l: LessThanEqual    => l.prettyPrint
      case g: GreaterThenEqual => g.prettyPrint
      case g: GreaterThen      => g.prettyPrint
      case n: NotEqual         => n.prettyPrint
      case e: Equal            => e.prettyPrint
      case o: Or               => o.prettyPrint
      case a: And              => a.prettyPrint
      case n: Negate           => n.prettyPrint
      case i: IntegerAnswer    => i.toString
      case d: DecimalAnswer    => d.toString
      case m: MoneyAnswer      => m.toString
      case b: BooleanAnswer    => b.toString
      case s: StringAnswer     => s.toString
      case d: DateAnswer       => d.toString
    }
  }

  implicit class ReferenceCanPrettyPrint(reference: Reference) extends PrettyPrintable[Reference] {
    override def prettyPrint: String = {
      reference.questionId
    }
  }

  implicit class AdditionCanPrettyPrint(addition: Addition) extends PrettyPrintable[Addition] {
    override def prettyPrint: String = {
      s"(${addition.left.prettyPrint} + ${addition.right.prettyPrint})"
    }
  }

  implicit class SubtractCanPrettyPrint(subtract: Subtract) extends PrettyPrintable[Subtract] {
    override def prettyPrint: String = {
      s"(${subtract.left.prettyPrint} - ${subtract.right.prettyPrint})"
    }
  }

  implicit class MultiplyCanPrettyPrint(multiply: Multiply) extends PrettyPrintable[Multiply] {
    override def prettyPrint: String = {
      s"(${multiply.left.prettyPrint} * ${multiply.right.prettyPrint})"
    }
  }

  implicit class DivideCanPrettyPrint(divide: Divide) extends PrettyPrintable[Divide] {
    override def prettyPrint: String = {
      s"(${divide.left.prettyPrint} / ${divide.right.prettyPrint})"
    }
  }

  implicit class MinusCanPrettyPrint(minus: Minus) extends PrettyPrintable[Minus] {
    override def prettyPrint: String = {
      s"-${minus.right.prettyPrint})"
    }
  }

  implicit class LessThanCanPrettyPrint(lessThan: LessThan) extends PrettyPrintable[LessThan] {
    override def prettyPrint: String = {
      s"(${lessThan.left.prettyPrint} < ${lessThan.right.prettyPrint})"
    }
  }

  implicit class LessThanEqualCanPrettyPrint(lessThanEqual: LessThanEqual) extends PrettyPrintable[LessThanEqual] {
    override def prettyPrint: String = {
      s"(${lessThanEqual.left.prettyPrint} =< ${lessThanEqual.right.prettyPrint})"
    }
  }

  implicit class GreaterThenEqualCanPrettyPrint(greaterThenEqual: GreaterThenEqual) extends PrettyPrintable[GreaterThenEqual] {
    override def prettyPrint: String = {
      s"(${greaterThenEqual.left.prettyPrint} >= ${greaterThenEqual.right.prettyPrint})"
    }
  }

  implicit class GreaterThenCanPrettyPrint(greaterThen: GreaterThen) extends PrettyPrintable[GreaterThen] {
    override def prettyPrint: String = {
      s"(${greaterThen.left.prettyPrint} > ${greaterThen.right.prettyPrint})"
    }
  }

  implicit class NotEqualCanPrettyPrint(notEqual: NotEqual) extends PrettyPrintable[NotEqual] {
    override def prettyPrint: String = {
      s"(${notEqual.left.prettyPrint} != ${notEqual.right.prettyPrint})"
    }
  }

  implicit class EqualCanPrettyPrint(equal: Equal) extends PrettyPrintable[Equal] {
    override def prettyPrint: String = {
      s"(${equal.left.prettyPrint} == ${equal.right.prettyPrint})"
    }
  }

  implicit class OrCanPrettyPrint(or: Or) extends PrettyPrintable[Or] {
    override def prettyPrint: String = {
      s"(${or.left.prettyPrint} || ${or.right.prettyPrint})"
    }
  }

  implicit class AndCanPrettyPrint(and: And) extends PrettyPrintable[And] {
    override def prettyPrint: String = {
      s"(${and.left.prettyPrint} && ${and.right.prettyPrint})"
    }
  }

  implicit class NegateCanPrettyPrint(negate: Negate) extends PrettyPrintable[Negate] {
    override def prettyPrint: String = {
      s"!${negate.right.prettyPrint})"
    }
  }
}
