import ql.models.ast._
import ql.grammar._
import ql.visitors._
import ql.validators._
import ql.spec.helpers._

import scala.io.Source
import scala.util.{Try, Success, Failure}

import org.scalatest.FunSpec
import org.scalatest.Matchers._
import org.scalatest.BeforeAndAfter

import org.antlr.v4.runtime._
import org.antlr.v4.runtime.tree._

class BinOpConditional extends FunSpec with BeforeAndAfter {
  val resourceDir = "ql/typechecking/conditions/binop"

  describe("conjunction") {
    describe("containing a left nested boolean and money binop") {
      val filename = s"${resourceDir}/nested/invalid_left_nested.ql"
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

    describe("containing a right nested boolean and money binop") {
      val filename = s"${resourceDir}/nested/invalid_right_nested.ql"
      val form = FormHelper.getForm(getClass.getResource(filename))
      val typechecker = new TypeChecker(form)

      it("check method should return false") {
        assert(!typechecker.check())
      }

      it("validate method should return a failure") {
        ConditionalValidator.validate(form) match {
          case Failure(ConditionalNotBoolean(_)) => succeed
          case other => fail("ConditionalValidator should have thrown an error")
        }
      }
    }

    describe("containing two valid binops on both sides") {
      val filename = s"${resourceDir}/nested/both_valid_nested.ql"
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

    describe("containing two invalid binops on both sides") {
      val filename = s"${resourceDir}/nested/both_invalid_nested.ql"
      val form = FormHelper.getForm(getClass.getResource(filename))
      val typechecker = new TypeChecker(form)

      it("check method should return false") {
        assert(!typechecker.check())
      }

      it("validate method should return a failure") {
        ConditionalValidator.validate(form) match {
          case Failure(ConditionalNotBoolean(_)) => succeed
          case other => fail("ConditionalValidator should have thrown an error")
        }
      }
    }
  }

  describe("disjunction") {
    describe("containing two booleans") {
      val filename = s"${resourceDir}/dis/valid.ql"
      val form = FormHelper.getForm(getClass.getResource(filename))
      val typechecker = new TypeChecker(form)

      it("check method should return true") {
        assert(typechecker.check())
      }

      it("validate method should not throw an exception") {
        ConditionalValidator.validate(form) match {
          case Failure(e) => fail("ConditionalValidator should not have thrown an error")
          case Success(_) => succeed
        }
      }
    }

    describe("containing a boolean and money") {
      val filename = s"${resourceDir}/dis/invalid.ql"
      val form = FormHelper.getForm(getClass.getResource(filename))
      val typechecker = new TypeChecker(form)

      it("check method should return true") {
        assert(!typechecker.check())
      }

      it("validate method should not throw an exception") {
        ConditionalValidator.validate(form) match {
          case Success(_) => fail("ConditionalValidator should not have thrown an error")
          case Failure(_) => succeed
        }
      }
    }
  }
}
