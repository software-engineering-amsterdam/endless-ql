import ql.models.ast._
import ql.spec.helpers._

import org.scalatest.FunSpec
import org.scalatest.Matchers._

class UnaryParser extends FunSpec {
  describe("when parsing a form containing arithmetic tokens") {
    it("should contain one unary negation") {
      val expressions =
        FormHelper.getExpressions(getClass.getResource("ql/unary/not.ql"))
      val expected = UnaryNotOp(Identifier("hasBoughtHouse"))

      expressions should contain(expected)
    }

    it("should contain two unary negations") {
      val expressions =
        FormHelper.getExpressions(getClass.getResource("ql/unary/notnot.ql"))
      val expected = UnaryNotOp(UnaryNotOp(Identifier("hasBoughtHouse")))

      expressions should contain(expected)
    }

    it("should contain an unary min") {
      val expressions =
        FormHelper.getExpressions(getClass.getResource("ql/unary/min.ql"))
      val expected = UnaryMinOp(Identifier("hasBoughtHouse"))

      expressions should contain(expected)
    }
  }
}
