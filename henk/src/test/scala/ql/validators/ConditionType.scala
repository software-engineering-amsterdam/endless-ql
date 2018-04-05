import ql.validators._
import ql.spec.helpers._

import org.scalatest.FunSpec
import org.scalatest.Matchers._

class ConditionTypeValidatorSpec extends FunSpec {
  val resourceDir = "ql/typechecking/conditions"
  val validator = new ConditionalValidator()

  describe("when ConditionalValidator contains a conditional with Boolean type") {
    val filename = s"${resourceDir}/simple.ql"
    val form = FormHelper.getRoot(getClass.getResource(filename))

    it("check should not return an option exception") {
      noException should be thrownBy validator.execute(form)
    }
  }

  describe("when ConditionalValidator contains a conditional with Money type") {
    val filename = s"${resourceDir}/money_type_conditional.ql"
    val form = FormHelper.getRoot(getClass.getResource(filename))

    it("should raise an exception") {
      an [ConditionalNotBoolean] should be thrownBy validator.execute(form)
    }
  }

  describe("when ConditionalValidator contains a valid binOp") {
    val filename = s"${resourceDir}/binop/simple_binop.ql"
    val form = FormHelper.getRoot(getClass.getResource(filename))

    it("should not raise an exception") {
      noException should be thrownBy validator.execute(form)
    }
  }

  describe(
    "when ConditionalValidator contains a binop consisting of money and boolean") {
    val filename = s"${resourceDir}/binop/money_bool_binop.ql"
    val form = FormHelper.getRoot(getClass.getResource(filename))

    it("should raise an exception") {
      an [ConditionalNotBoolean] should be thrownBy validator.execute(form)
    }
  }
}
