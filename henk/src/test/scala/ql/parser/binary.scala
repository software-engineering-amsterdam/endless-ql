import ql.models.ast._
import ql.grammar._
import ql.visitors._
import ql.parsers._

import scala.io.Source

import org.scalatest.FunSpec
import org.scalatest.Matchers._
import org.scalatest.BeforeAndAfter

import org.antlr.v4.runtime._
import org.antlr.v4.runtime.tree._

class ArithmeticParser extends FunSpec with BeforeAndAfter {
  // maybe extract method to general helper class
  private def getFlattenedForm(location: String): List[ASTNode] = {
    val form = QlFormParser.parseFromURL(getClass.getResource(location))
    QlFormParser.flattenNT(form)
  }

  describe("when parsing a form containing arithmetic tokens") {
    it("should contain one logical conjunction") {
      val result = getFlattenedForm("ql/binary/and.ql")
      val expected =
        ASTBinary(ASTIdentifier("hasSoldHouse"),
                  ASTIdentifier("hasBoughtHouse"),
                  ASTLogicalCon())

      assert(result.filter(x => x == expected).size == 1)
    }

    it("should contain one logical disjunction") {
      val result = getFlattenedForm("ql/binary/or.ql")
      val expected =
        ASTBinary(ASTIdentifier("hasSoldHouse"),
                  ASTIdentifier("hasBoughtHouse"),
                  ASTLogicalDis())

      assert(result.filter(x => x == expected).size == 1)
    }

    it("should contain one logical eq") {
      val result = getFlattenedForm("ql/binary/eq.ql")
      val expected =
        ASTBinary(ASTIdentifier("hasSoldHouse"),
                  ASTIdentifier("hasBoughtHouse"),
                  ASTRelationalEQ())

      assert(result.filter(x => x == expected).size == 1)
    }

    it("should contain one logical gt") {
      val result = getFlattenedForm("ql/binary/gt.ql")
      val expected =
        ASTBinary(ASTIdentifier("hasSoldHouse"),
                  ASTIdentifier("hasBoughtHouse"),
                  ASTRelationalGT())

      assert(result.filter(x => x == expected).size == 1)
    }

    it("should contain one logical gte") {
      val result = getFlattenedForm("ql/binary/gte.ql")
      val expected =
        ASTBinary(ASTIdentifier("hasSoldHouse"),
                  ASTIdentifier("hasBoughtHouse"),
                  ASTRelationalGTE())

      assert(result.filter(x => x == expected).size == 1)
    }

    it("should contain one logical lt") {
      val result = getFlattenedForm("ql/binary/lt.ql")
      val expected =
        ASTBinary(ASTIdentifier("hasSoldHouse"),
                  ASTIdentifier("hasBoughtHouse"),
                  ASTRelationalLT())

      assert(result.filter(x => x == expected).size == 1)
    }

    it("should contain one logical lte") {
      val result = getFlattenedForm("ql/binary/lte.ql")
      val expected =
        ASTBinary(ASTIdentifier("hasSoldHouse"),
                  ASTIdentifier("hasBoughtHouse"),
                  ASTRelationalLTE())

      assert(result.filter(x => x == expected).size == 1)
    }

    it("should contain one logical neq") {
      val result = getFlattenedForm("ql/binary/ne.ql")
      val expected =
        ASTBinary(ASTIdentifier("hasSoldHouse"),
                  ASTIdentifier("hasBoughtHouse"),
                  ASTRelationalNE())

      assert(result.filter(x => x == expected).size == 1)
    }
  }
}
