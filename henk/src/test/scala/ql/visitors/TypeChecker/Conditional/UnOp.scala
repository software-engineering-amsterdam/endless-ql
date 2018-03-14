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

class UnOpConditionalSpec extends FunSpec with BeforeAndAfter {
  val resourceDir = "ql/typechecking/conditions/unop"

  describe("containing invalid not money expression in unary") {
    val filename = s"${resourceDir}/not_money_unop.ql"
    val form = FormHelper.getForm(getClass.getResource(filename))
    val typechecker = new TypeChecker(form)

    it("check method should return true") {
      assert(!typechecker.check())
    }

    it("validate method should not throw an exception") {
      ConditionalValidator.validate(form) match {
        case Failure(ConditionalNotBoolean(_)) => succeed
        case other => fail("ConditionalValidator should have thrown an error")
      }
    }
  }

  describe("containing valid not boolean expression in unary") {
    val filename = s"${resourceDir}/not_boolean_unop.ql"
    val form = FormHelper.getForm(getClass.getResource(filename))
    val typechecker = new TypeChecker(form)

    it("check method should return true") {
      assert(typechecker.check())
    }

    it("validate method should not return a Failure") {
      ConditionalValidator.validate(form) match {
        case Failure(e) => fail("ConditionalValidator should not have thrown an error")
        case Success(_) => succeed
      }
    }
  }

  describe("containing valid binop expression in unary") {
    val filename = s"${resourceDir}/not_binop_unop.ql"
    val form = FormHelper.getForm(getClass.getResource(filename))
    val typechecker = new TypeChecker(form)

    it("check method should return true") {
      assert(typechecker.check())
    }

    it("validate method should not return a Failure") {
      ConditionalValidator.validate(form) match {
        case Failure(e) => fail("ConditionalValidator should not have thrown an error")
        case Success(_) => succeed
      }
    }
  }
}
