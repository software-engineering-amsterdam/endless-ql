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

class UnOpConditionalSpec extends FunSpec with BeforeAndAfter {
  // maybe extract method to general helper class
  private def getForm(location: String): ASTNode = {
    return QlFormParser.parseFromURL(getClass.getResource(location))
  }

  val resourceDir = "ql/typechecking/conditions/unop"

  describe("invalid not money expression in unary") {
    val filename = s"${resourceDir}/not_money_unop.ql"
    val form = getForm(filename)
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

  describe("valid not boolean expression in unary") {
    val filename = s"${resourceDir}/not_boolean_unop.ql"
    val form = getForm(filename)
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

  describe("valid binOp in unary not") {
    val filename = s"${resourceDir}/not_binop_unop.ql"
    val form = getForm(filename)
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
