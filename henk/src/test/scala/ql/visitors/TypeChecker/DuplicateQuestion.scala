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

class DuplicateQuestionSpec extends FunSpec with BeforeAndAfter {
  val resourceDir = "ql/typechecking/duplicate_question"
  val validator = new DuplicateQuestionValidator()

  describe("when form contains no duplicate questions") {
    val filename = s"${resourceDir}/simple.ql"
    val form = FormHelper.getForm(getClass.getResource(filename))

    it("check should not return an option exception") {
      validator.execute(form) match {
        case None => succeed
        case Some(DuplicateQuestionDeclaration(e)) => fail(e)
        case other => fail("ConditionalValidator should not have thrown an error")
      }
    }
  }

  describe("when form contains a duplicate question with same type") {
    val filename = s"${resourceDir}/duplicate_same_type.ql"
    val form = FormHelper.getForm(getClass.getResource(filename))

    it("check should not return an option exception") {
      validator.execute(form) match {
        case None => succeed
        case Some(DuplicateQuestionDeclaration(e)) => fail(e)
        case other => fail("ConditionalValidator should not have thrown an error")
      }
    }
  }

  describe("when form contains a duplicate question with different type") {
    val filename = s"${resourceDir}/duplicate_different_type.ql"
    val form = FormHelper.getForm(getClass.getResource(filename))

    it("check should return an option exception") {
      validator.execute(form) match {
        case None => fail()
        case Some(DuplicateQuestionDeclaration(e)) => succeed
        case other => fail("wrong error thrown")
      }
    }
  }
}
