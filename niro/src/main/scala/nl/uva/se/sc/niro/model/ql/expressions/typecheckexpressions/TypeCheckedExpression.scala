package nl.uva.se.sc.niro.model.ql.expressions.typecheckexpressions

import nl.uva.se.sc.niro.model.ql.expressions.typecheckexpressions.Implicits.{ Addable, Divideable, Logical, Minusable, Multipliable, Orderable, Subtractable, WiderThan }

abstract class Expression[T] {
  def evaluate: Answer[T]
}

abstract class Answer[T](value: T) extends Expression[T] {
  def evaluate: Answer[T] = this

  def get: T = value
}

case class IntValue(value: Int) extends Answer[Int](value) {}
case class DoubleValue(value: Double) extends Answer[Double](value) {}
case class StringValue(value: String) extends Answer[String](value) {}
case class BooleanValue(value: Boolean) extends Answer[Boolean](value) {}
case object NullValue extends Answer(null) {}

final case class Reference[T, R](questionId: String) extends Expression[R] {
  def evaluate: Answer[R] = ???
}

object Implicits {

  trait Addable[T] {
    def plus(self: T, other: T): Answer[T]
  }

  trait Subtractable[T] {
    def subtract(self: T, other: T): Answer[T]
  }

  trait Multipliable[T] {
    def multiply(self: T, other: T): Answer[T]
  }

  trait Divideable[T] {
    def divide(self: T, other: T): Answer[T]
  }

  trait Minusable[T] {
    def minus(self: T): Answer[T]
  }

  trait Orderable[T] {
    def lessThan(self: T, other: T): Answer[BooleanValue]

    def lessThanEquals(self: T, other: T): Answer[BooleanValue]

    def greaterThenEquals(self: T, other: T): Answer[BooleanValue]

    def greaterThen(self: T, other: T): Answer[BooleanValue]

    def notEquals(self: T, other: T): Answer[BooleanValue]

    def equals(self: T, other: T): Answer[BooleanValue]
  }

  trait Logical[T] {
    def and(self: T, other: T): Answer[BooleanValue]

    def or(self: T, other: T): Answer[BooleanValue]

    def negate(x: T): Answer[BooleanValue]
  }

  implicit object IntAddable extends Addable[Int] {
    def plus(self: Int, other: Int) = IntValue(self + other)
  }

  implicit object DoubleAddable extends Addable[Double] {
    def plus(self: Double, other: Double) = DoubleValue(self + other)
  }

  implicit object StringAddable extends Addable[String] {
    def plus(self: String, other: String) = StringValue(self + other)
  }

  trait =!=[A, B]
  implicit def neq[A, B] : A =!= B = null
  implicit def neqAmbig1[A] : A =!= A = null
  implicit def neqAmbig2[B] : B =!= B = null

  implicit def widenOne[T, U](v1: Answer[T])(conv: (Answer[T] => Answer[U])): (Answer[U]) =
    conv(v1)

  implicit def reflexiveWiden[T](v1: Answer[T]): Answer[T] = v1

  trait LT[T, U]
  implicit def intDoubleLT: LT[Int, Double] = null

  trait WiderThan[T, U, V] {}
  implicit def rightWider[T, U, V]
  (implicit rw: LT[T, U],
   conv2: (Answer[U] => Answer[V]),
   conv1: (Answer[T] => Answer[V])): WiderThan[T,U,V] = null

  implicit def leftWider[T, U, V]
  (implicit rw: LT[U, T],
   conv2: (Answer[T] => Answer[V]),
   conv1: (Answer[U] => Answer[V])): WiderThan[T,U,V] = null

  implicit def reflWider[T]: WiderThan[T, T, T] = null

}

final case class Addition[T, U, V]
  (left: Expression[T], right: Expression[U])
  (implicit lub: WiderThan[T, U, V],
            widenLeft: Answer[T] => Answer[V],
            widenRight: Answer[U] => Answer[V],
            ev: Addable[V]) extends Expression[V] {
  override def evaluate: Answer[V] = {
    val lv = widenLeft(left.evaluate)
    val rv = widenRight(right.evaluate)
    ev.plus(lv.get, rv.get)
  }
}

final case class Subtract[T](left: Expression[T], right: Expression[T])(implicit ev: Subtractable[T]) extends Expression[T] {
  override def evaluate: Answer[T] = {
    val lv = left.evaluate
    val rv = right.evaluate
    ev.subtract(lv.get, rv.get)
  }
}

final case class Multiply[T](left: Expression[T], right: Expression[T])(implicit ev: Multipliable[T]) extends Expression[T] {
  override def evaluate: Answer[T] = {
    val lv = left.evaluate
    val rv = right.evaluate
    ev.multiply(lv.get, rv.get)
  }
}

final case class Divide[T](left: Expression[T], right: Expression[T])(implicit ev: Divideable[T]) extends Expression[T] {
  override def evaluate: Answer[T] = {
    val lv = left.evaluate
    val rv = right.evaluate
    ev.divide(lv.get, rv.get)
  }
}

final case class Minus[T](left: Expression[T])(implicit ev: Minusable[T]) extends Expression[T] {
  override def evaluate: Answer[T] = {
    val lv = left.evaluate
    ev.minus(lv.get)
  }
}

final case class LessThan[T](left: Expression[T], right: Expression[T])(implicit ev: Orderable[T]) extends Expression[BooleanValue] {
  def evaluate: Answer[BooleanValue] = {
    val lv = left.evaluate
    val rv = right.evaluate
    ev.lessThan(lv.get, rv.get)
  }
}

final case class LessThanEqual[T](left: Expression[T], right: Expression[T])(implicit ev: Orderable[T]) extends Expression[BooleanValue] {
  override def evaluate: Answer[BooleanValue] = {
    val lv = left.evaluate
    val rv = right.evaluate
    ev.lessThanEquals(lv.get, rv.get)
  }
}

final case class GreaterThenEqual[T](left: Expression[T], right: Expression[T])(implicit ev: Orderable[T]) extends Expression[BooleanValue] {
  override def evaluate: Answer[BooleanValue] = {
    val lv = left.evaluate
    val rv = right.evaluate
    ev.greaterThenEquals(lv.get, rv.get)
  }
}

final case class GreaterThen[T](left: Expression[T], right: Expression[T])(implicit ev: Orderable[T]) extends Expression[BooleanValue] {
  override def evaluate: Answer[BooleanValue] = {
    val lv = left.evaluate
    val rv = right.evaluate
    ev.greaterThen(lv.get, rv.get)
  }
}

final case class NotEqual[T](left: Expression[T], right: Expression[T])(implicit ev: Orderable[T]) extends Expression[BooleanValue] {
  override def evaluate: Answer[BooleanValue] = {
    val lv = left.evaluate
    val rv = right.evaluate
    ev.notEquals(lv.get, rv.get)
  }
}

final case class Equal[T](left: Expression[T], right: Expression[T])(implicit ev: Orderable[T]) extends Expression[BooleanValue] {
  override def evaluate: Answer[BooleanValue] = {
    val lv = left.evaluate
    val rv = right.evaluate
    ev.equals(lv.get, rv.get)
  }
}

final case class Or[T](left: Expression[T], right: Expression[T])(implicit ev: Logical[T]) extends Expression[BooleanValue] {
  override def evaluate: Answer[BooleanValue] = {
    val lv = left.evaluate
    val rv = right.evaluate
    ev.or(lv.get, rv.get)
  }
}

final case class And[T](left: Expression[T], right: Expression[T])(implicit ev: Logical[T]) extends Expression[BooleanValue] {
  override def evaluate: Answer[BooleanValue] = {
    val lv = left.evaluate
    val rv = right.evaluate
    ev.and(lv.get, rv.get)
  }
}

final case class Negate[T](left: Expression[T], right: Expression[T])(implicit ev: Logical[T]) extends Expression[BooleanValue] {
  override def evaluate: Answer[BooleanValue] = {
    val lv = left.evaluate
    ev.negate(lv.get)
  }
}