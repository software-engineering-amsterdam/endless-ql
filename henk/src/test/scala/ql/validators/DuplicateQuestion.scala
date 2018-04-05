import ql.models.ast._
import ql.validators._
import ql.spec.helpers._

import org.scalatest.FunSpec
import org.scalatest.Matchers._

class DuplicateQuestionSpec extends FunSpec {
  val resourceDir = "ql/typechecking/duplicate_question"
  val validator = new DuplicateQuestionValidator()

  describe("when form contains no duplicate questions") {
    val filename = s"${resourceDir}/simple.ql"
    val form = FormHelper.getRoot(getClass.getResource(filename))

    it("execute should not raise an exception") {
      noException should be thrownBy validator.execute(form)
    }
  }

  describe("when form contains a duplicate question with same type") {
    val filename = s"${resourceDir}/duplicate_same_type.ql"
    val form = FormHelper.getRoot(getClass.getResource(filename))

    it("check should not return an option exception") {
      noException should be thrownBy validator.execute(form)
    }
  }

  describe("when form contains a duplicate question with different type") {
    val filename = s"${resourceDir}/duplicate_different_type.ql"
    val form = FormHelper.getRoot(getClass.getResource(filename))

    it("execute should raise an exception") {
      an [DuplicateQuestionDeclaration] should be thrownBy validator.execute(form)
    }
  }
}
