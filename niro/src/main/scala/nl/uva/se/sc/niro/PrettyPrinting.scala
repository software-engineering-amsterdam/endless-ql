package nl.uva.se.sc.niro

import nl.uva.se.sc.niro.model.ql.expressions._
import nl.uva.se.sc.niro.model.ql.expressions.answers._

trait PrettyPrintable[T] {
  def pprint: String
}


object PrettyPrinter {
  implicit class ExpressionCanPrettyPrint(expression: Expression) extends PrettyPrintable[Expression] {
    override def pprint: String = expression match {
      case r: Reference        => r.pprint
      case a: Addition         => a.pprint
      case s: Subtract         => s.pprint
      case m: Multiply         => m.pprint
      case d: Divide           => d.pprint
      case m: Minus            => m.pprint
      case l: LessThan         => l.pprint
      case l: LessThanEqual    => l.pprint
      case g: GreaterThenEqual => g.pprint
      case g: GreaterThen      => g.pprint
      case n: NotEqual         => n.pprint
      case e: Equal            => e.pprint
      case o: Or               => o.pprint
      case a: And              => a.pprint
      case n: Negate           => n.pprint
      case i: IntegerAnswer    => i.value.toString
      case d: DecimalAnswer    => d.value.toString
      case m: MoneyAnswer      => m.value.toString
      case b: BooleanAnswer    => b.value.toString
      case s: StringAnswer     => s.value.toString
      case d: DateAnswer       => d.value.toString
    }
  }

  implicit class ReferenceCanPrettyPrint(reference: Reference) extends PrettyPrintable[Reference] {
    override def pprint: String = {
      reference.questionId
    }
  }

  implicit class AdditionCanPrettyPrint(addition: Addition) extends PrettyPrintable[Addition] {
    override def pprint: String = {
      s"(${addition.left.pprint} + ${addition.right.pprint})"
    }
  }

  implicit class SubtractCanPrettyPrint(subtract: Subtract) extends PrettyPrintable[Subtract] {
    override def pprint: String = {
      s"(${subtract.left.pprint} - ${subtract.right.pprint})"
    }
  }

  implicit class MultiplyCanPrettyPrint(multiply: Multiply) extends PrettyPrintable[Multiply] {
    override def pprint: String = {
      s"(${multiply.left.pprint} * ${multiply.right.pprint})"
    }
  }

  implicit class DivideCanPrettyPrint(divide: Divide) extends PrettyPrintable[Divide] {
    override def pprint: String = {
      s"(${divide.left.pprint} / ${divide.right.pprint})"
    }
  }

  implicit class MinusCanPrettyPrint(minus: Minus) extends PrettyPrintable[Minus] {
    override def pprint: String = {
      s"-${minus.right.pprint})"
    }
  }

  implicit class LessThanCanPrettyPrint(lessThan: LessThan) extends PrettyPrintable[LessThan] {
    override def pprint: String = {
      s"(${lessThan.left.pprint} < ${lessThan.right.pprint})"
    }
  }

  implicit class LessThanEqualCanPrettyPrint(lessThanEqual: LessThanEqual) extends PrettyPrintable[LessThanEqual] {
    override def pprint: String = {
      s"(${lessThanEqual.left.pprint} =< ${lessThanEqual.right.pprint})"
    }
  }

  implicit class GreaterThenEqualCanPrettyPrint(greaterThenEqual: GreaterThenEqual) extends PrettyPrintable[GreaterThenEqual] {
    override def pprint: String = {
      s"(${greaterThenEqual.left.pprint} >= ${greaterThenEqual.right.pprint})"
    }
  }

  implicit class GreaterThenCanPrettyPrint(greaterThen: GreaterThen) extends PrettyPrintable[GreaterThen] {
    override def pprint: String = {
      s"(${greaterThen.left.pprint} > ${greaterThen.right.pprint})"
    }
  }

  implicit class NotEqualCanPrettyPrint(notEqual: NotEqual) extends PrettyPrintable[NotEqual] {
    override def pprint: String = {
      s"(${notEqual.left.pprint} != ${notEqual.right.pprint})"
    }
  }

  implicit class EqualCanPrettyPrint(equal: Equal) extends PrettyPrintable[Equal] {
    override def pprint: String = {
      s"(${equal.left.pprint} == ${equal.right.pprint})"
    }
  }

  implicit class OrCanPrettyPrint(or: Or) extends PrettyPrintable[Or] {
    override def pprint: String = {
      s"(${or.left.pprint} || ${or.right.pprint})"
    }
  }

  implicit class AndCanPrettyPrint(and: And) extends PrettyPrintable[And] {
    override def pprint: String = {
      s"(${and.left.pprint} && ${and.right.pprint})"
    }
  }

  implicit class NegateCanPrettyPrint(negate: Negate) extends PrettyPrintable[Negate] {
    override def pprint: String = {
      s"!${negate.right.pprint})"
    }
  }
}
