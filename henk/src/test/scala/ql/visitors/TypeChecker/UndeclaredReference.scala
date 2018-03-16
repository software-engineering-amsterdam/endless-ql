import ql.models.ast._
import ql.validators._
import ql.spec.helpers._

import scala.io.Source
import scala.util.{ Try, Success, Failure }

import org.scalatest.FunSpec
import org.scalatest.Matchers._
import org.scalatest.BeforeAndAfter

import org.antlr.v4.runtime._
import org.antlr.v4.runtime.tree._

class UndeclaredReferenceSpec extends FunSpec with BeforeAndAfter {
  val resourceDir = "ql/typechecking"

  describe("when typechecking a simple valid form") {
    val filename = s"${resourceDir}/simple.ql"
    val form = FormHelper.getForm(getClass.getResource(filename))

    it("check should not return an option exception") {
      IdentifierValidator.check(form) match {
        case None => succeed
        case Some(IdentifierNotDeclared(e)) => fail(e)
        case other => fail("ConditionalValidator should not have thrown an error")
      }
    }
  }

  describe("when typechecking a form with a single undeclared identifier") {
    val filename = s"${resourceDir}/single_undeclared_identifier.ql"
    val form = FormHelper.getForm(getClass.getResource(filename))

    it("check should return an option exception") {
      IdentifierValidator.check(form) match {
        case None => fail()
        case Some(IdentifierNotDeclared(e)) => succeed
        case other => fail("wrong error thrown")
      }
    }
  }

  describe("when typechecking a form with multiple undeclared identifiers") {
    val filename = s"${resourceDir}/multiple_undeclared_identifiers.ql"
    val form = FormHelper.getForm(getClass.getResource(filename))

    it("check should return an option exception") {
      IdentifierValidator.check(form) match {
        case None => fail()
        case Some(IdentifierNotDeclared(e)) => succeed
        case other => fail("wrong error thrown")
      }
    }
  }
}
