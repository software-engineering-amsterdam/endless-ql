import ql.models.ast._
import ql.spec.helpers._

import org.scalatest.FunSpec
import org.scalatest.Matchers._

class ParserTypeStringSpec extends FunSpec {
  describe("parsing a form containg a string literal") {
    it("should contain an ASTStringValue") {
      val expressions =
        FormHelper.getExpressions(getClass.getResource("ql/parser/types/string.ql"))
      val expected = StringValue("Amsterdam")

      expressions should contain(expected)
    }
  }
}
