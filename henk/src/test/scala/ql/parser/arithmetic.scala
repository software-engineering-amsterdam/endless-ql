import ql.models.ast._
import ql.spec.helpers._

import org.scalatest.FunSpec
import org.scalatest.Matchers._

class ArithmeticParserSpec extends FunSpec {
  describe("when parsing a conditional containing arithmetic binop") {
    it("should contain a single question") {
      val expressions =
        FormHelper.getExpressions(getClass.getResource("ql/arithmetic/simple_conditional.ql"))

      val expected = RelationalLTOp(
        Identifier("amount"), AddOp(IntegerValue(1), IntegerValue(1)))

      expressions should contain(expected)
    }
  }
}
