import ql.models.ast._
import ql.validators._
import ql.spec.helpers._

import org.scalatest.FunSpec
import org.scalatest.Matchers._

class IdentifierValidatorSpec extends FunSpec {
  val resourceDir = "ql/typechecking"
  val validator = new IdentifierValidator()

  describe("when typechecking a simple valid form") {
    val filename = s"${resourceDir}/simple.ql"
    val form = FormHelper.getRoot(getClass.getResource(filename))

    it("execute should not raise an exception") {
      noException should be thrownBy validator.execute(form)
    }
  }

  describe("when typechecking a form with a single undeclared identifier") {
    val filename = s"${resourceDir}/single_undeclared_identifier.ql"
    val form = FormHelper.getRoot(getClass.getResource(filename))

    it("execute should raise an exception") {
      an [IdentifierNotDeclared] should be thrownBy validator.execute(form)
    }
  }

  describe("when typechecking a form with multiple undeclared identifiers") {
    val filename = s"${resourceDir}/multiple_undeclared_identifiers.ql"
    val form = FormHelper.getRoot(getClass.getResource(filename))

    it("check should return an option exception") {
      an [IdentifierNotDeclared] should be thrownBy validator.execute(form)
    }
  }
}
