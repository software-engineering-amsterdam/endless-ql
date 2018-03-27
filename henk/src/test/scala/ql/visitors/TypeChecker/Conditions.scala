import ql.models.ast._
import ql.validators._
import ql.spec.helpers._

import scala.io.Source
import scala.util.{Try, Success, Failure}

import org.scalatest.FunSpec
import org.scalatest.Matchers._
import org.scalatest.BeforeAndAfter

import org.antlr.v4.runtime._
import org.antlr.v4.runtime.tree._

class ConditionTypeSpec extends FunSpec with BeforeAndAfter {
  val resourceDir = "ql/typechecking/conditions"
  val validator = new ConditionalValidator()

  describe("when ConditionalValidator contains a conditional with Boolean type") {
    val filename = s"${resourceDir}/simple.ql"
    val form = FormHelper.getRoot(getClass.getResource(filename))

    it("check should not return an option exception") {
      validator.check(form) match {
        case None                           => succeed
        case Some(ConditionalNotBoolean(e)) => fail(e)
        case other =>
          fail("ConditionalValidator should not have thrown an error")
      }
    }
  }

  describe("when ConditionalValidator contains a conditional with Money type") {
    val filename = s"${resourceDir}/money_type_conditional.ql"
    val form = FormHelper.getRoot(getClass.getResource(filename))

    it("check should return an option exception") {
      validator.check(form) match {
        case None                           => fail()
        case Some(ConditionalNotBoolean(e)) => succeed
        case other                          => fail("wrong error thrown")
      }
    }
  }

  describe("when ConditionalValidator contains a valid binOp") {
    val filename = s"${resourceDir}/binop/simple_binop.ql"
    val form = FormHelper.getRoot(getClass.getResource(filename))

    it("check should not return an option exception") {
      validator.check(form) match {
        case None                           => succeed
        case Some(ConditionalNotBoolean(e)) => fail(e)
        case other =>
          fail("ConditionalValidator should not have thrown an error")
      }
    }
  }

  describe(
    "when ConditionalValidator contains a binop consisting of money and boolean") {
    val filename = s"${resourceDir}/binop/money_bool_binop.ql"
    val form = FormHelper.getRoot(getClass.getResource(filename))

    it("check should return an option exception") {
      validator.check(form) match {
        case None                           => fail()
        case Some(ConditionalNotBoolean(e)) => succeed
        case other                          => fail("wrong error thrown")
      }
    }
  }
}
