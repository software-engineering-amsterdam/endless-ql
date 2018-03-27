import ql.models.ast._
import ql.spec.helpers._

import org.scalatest.FunSpec
import org.scalatest.Matchers._

class ParserTypeBooleanSpec extends FunSpec {
  describe("parsing a form containg a true boolean literal") {
    it("should contain an ASTBooleanValue") {
      val expressions =
        FormHelper.getExpressions(getClass.getResource("ql/parser/types/boolean.ql"))
      val expected = BooleanValue(true)

      expressions should contain(expected)
    }
  }

  describe("parsing a form containg a false boolean literal") {
    it("should contain an ASTBooleanValue") {
      val expressions =
        FormHelper.getExpressions(getClass.getResource("ql/parser/types/false.ql"))
      val expected = BooleanValue(false)

      expressions should contain(expected)
    }
  }
}
