import ql.models.ast._
import ql.grammar._
import ql.visitors._
import ql.validators._
import ql.parsers._

import scala.io.Source
import scala.util.{Try, Success, Failure}

import org.scalatest.FunSpec
import org.scalatest.Matchers._
import org.scalatest.BeforeAndAfter

import org.antlr.v4.runtime._
import org.antlr.v4.runtime.tree._

class ConditionTypeSpec extends FunSpec with BeforeAndAfter {
  // maybe extract method to general helper class
  private def getForm(location: String): ASTNode = {
    return QlFormParser.parseFromURL(getClass.getResource(location))
  }

  describe("when ConditionalValidator contains a conditional with Boolean type") {
    val filename = "ql/typechecking/conditions/simple.ql"
    val form = getForm(filename)
    val typechecker = new TypeChecker(form)

    it("check method should return true") {
      assert(typechecker.check())
    }

    it("validate method should not throw an exception") {
      // noException should be thrownBy ConditionalValidator.validate(form)
      ConditionalValidator.validate(form) match {
        case Failure(e) => fail("ConditionalValidator should not have thrown an error")
        case Success(_) => succeed
      }
    }
  }

  describe("when ConditionalValidator contains a conditional with Money type") {
    val filename = "ql/typechecking/conditions/money_type_conditional.ql"
    val form = getForm(filename)
    val typechecker = new TypeChecker(form)

    it("check method should return false") {
      assert(!typechecker.check())
    }

    it("validate method should return an Option Failure") {
      ConditionalValidator.validate(form) match {
        case Failure(ConditionalNotBoolean(_)) => succeed
        case other => fail("ConditionalValidator should have thrown an error")
      }
    }
  }

  describe("when ConditionalValidator contains a valid binOp") {
    val filename = "ql/typechecking/conditions/binop/simple_binop.ql"
    val form = getForm(filename)
    val typechecker = new TypeChecker(form)

    it("check method should return false") {
      assert(typechecker.check())
    }

    it("validate method should return an Option Failure") {
      ConditionalValidator.validate(form) match {
        case Failure(e) => fail("ConditionalValidator should not have thrown an error")
        case Success(_) => succeed
      }
    }
  }

  describe("when ConditionalValidator contains a binop consisting of money and boolean") {
    val filename = "ql/typechecking/conditions/binop/money_bool_binop.ql"
    val form = getForm(filename)
    val typechecker = new TypeChecker(form)

    it("check method should return false") {
      assert(!typechecker.check())
    }

    it("validate method should return an Option Failure") {
      ConditionalValidator.validate(form) match {
        case Failure(ConditionalNotBoolean(_)) => succeed
        case other => fail("ConditionalValidator should have thrown an error")
      }
    }
  }
}