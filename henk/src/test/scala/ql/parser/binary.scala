import ql.models.ast._
import ql.spec.helpers._

import org.scalatest.FunSpec
import org.scalatest.Matchers._

class BinaryOpParserSpec extends FunSpec {
  describe("when parsing a form containing arithmetic tokens") {
    it("should contain one logical conjunction") {
      val expressions =
        FormHelper.getExpressions(getClass.getResource("ql/binary/and.ql"))
      val expected =
        LogicalConOp(Identifier("hasSoldHouse"), Identifier("hasBoughtHouse"))

      expressions should contain(expected)
    }

    it("should contain one logical disjunction") {
      val expressions =
        FormHelper.getExpressions(getClass.getResource("ql/binary/or.ql"))
      val expected =
        LogicalDisOp(Identifier("hasSoldHouse"), Identifier("hasBoughtHouse"))

      expressions should contain(expected)
    }

    it("should contain one logical eq") {
      val expressions =
        FormHelper.getExpressions(getClass.getResource("ql/binary/eq.ql"))
      val expected =
        EqualOp(Identifier("hasSoldHouse"), Identifier("hasBoughtHouse"))

      expressions should contain(expected)
    }

    it("should contain one logical gt") {
      val expressions =
        FormHelper.getExpressions(getClass.getResource("ql/binary/gt.ql"))
      val expected =
        RelationalGTOp(Identifier("hasSoldHouse"), Identifier("hasBoughtHouse"))

      expressions should contain(expected)
    }

    it("should contain one logical gte") {
      val expressions =
        FormHelper.getExpressions(getClass.getResource("ql/binary/gte.ql"))
      val expected =
        RelationalGTEOp(Identifier("hasSoldHouse"),
                        Identifier("hasBoughtHouse"))

      expressions should contain(expected)
    }

    it("should contain one logical lt") {
      val expressions =
        FormHelper.getExpressions(getClass.getResource("ql/binary/lt.ql"))
      val expected =
        RelationalLTOp(Identifier("hasSoldHouse"), Identifier("hasBoughtHouse"))

      expressions should contain(expected)
    }

    it("should contain one logical lte") {
      val expressions =
        FormHelper.getExpressions(getClass.getResource("ql/binary/lte.ql"))
      val expected =
        RelationalLTEOp(Identifier("hasSoldHouse"),
                        Identifier("hasBoughtHouse"))

      expressions should contain(expected)
    }

    it("should contain one logical neq") {
      val expressions =
        FormHelper.getExpressions(getClass.getResource("ql/binary/ne.ql"))
      val expected =
        NotEqualOp(Identifier("hasSoldHouse"), Identifier("hasBoughtHouse"))

      expressions should contain(expected)
    }
  }
}
