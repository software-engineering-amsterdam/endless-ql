import ql.models._
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
  private def getForm(location:String): ASTNode = {
    return QlFormParser.parseFromURL(getClass.getResource(location))
  }

  describe("when parsing a form containing arithmetic tokens") {
    it("should contain a logical conjunction") {
      val result = getForm("ql/binary/and.ql")
      val expected = ASTRoot(
        ASTFormHeader(),
        ASTFormBody(
          List(
            ASTQuestion(ASTVarDecl(ASTBoolean())),
            ASTQuestion(ASTVarDecl(ASTBoolean())),

            ASTIfStatement(
              ASTBinary(ASTIdentifier(), ASTIdentifier(), ASTLogicalCon()),
              List()
            )
          )
        )
      )
      assert(result == expected)
    }

    it("should contain a logical disjunction") {
      val result = getForm("ql/binary/or.ql")
      val expected = ASTRoot(
        ASTFormHeader(),
        ASTFormBody(
          List(
            ASTQuestion(ASTVarDecl(ASTBoolean())),
            ASTQuestion(ASTVarDecl(ASTBoolean())),

            ASTIfStatement(
              ASTBinary(ASTIdentifier(), ASTIdentifier(), ASTLogicalDis()),
              List()
            )
          )
        )
      )
      assert(result == expected)
    }

    it("should contain a logical eq") {
      val result = getForm("ql/binary/eq.ql")
      val expected = ASTRoot(
        ASTFormHeader(),
        ASTFormBody(
          List(
            ASTQuestion(ASTVarDecl(ASTBoolean())),
            ASTQuestion(ASTVarDecl(ASTBoolean())),

            ASTIfStatement(
              ASTBinary(ASTIdentifier(), ASTIdentifier(), ASTRelationalEQ()),
              List()
            )
          )
        )
      )
      assert(result == expected)
    }

    it("should contain a logical gt") {
      val result = getForm("ql/binary/gt.ql")
      val expected = ASTRoot(
        ASTFormHeader(),
        ASTFormBody(
          List(
            ASTQuestion(ASTVarDecl(ASTBoolean())),
            ASTQuestion(ASTVarDecl(ASTBoolean())),

            ASTIfStatement(
              ASTBinary(ASTIdentifier(), ASTIdentifier(), ASTRelationalGT()),
              List()
            )
          )
        )
      )
      assert(result == expected)
    }

    it("should contain a logical gte") {
      val result = getForm("ql/binary/gte.ql")
      val expected = ASTRoot(
        ASTFormHeader(),
        ASTFormBody(
          List(
            ASTQuestion(ASTVarDecl(ASTBoolean())),
            ASTQuestion(ASTVarDecl(ASTBoolean())),

            ASTIfStatement(
              ASTBinary(ASTIdentifier(), ASTIdentifier(), ASTRelationalGTE()),
              List()
            )
          )
        )
      )
      assert(result == expected)
    }

    it("should contain a logical lt") {
      val result = getForm("ql/binary/lt.ql")
      val expected = ASTRoot(
        ASTFormHeader(),
        ASTFormBody(
          List(
            ASTQuestion(ASTVarDecl(ASTBoolean())),
            ASTQuestion(ASTVarDecl(ASTBoolean())),

            ASTIfStatement(
              ASTBinary(ASTIdentifier(), ASTIdentifier(), ASTRelationalLT()),
              List()
            )
          )
        )
      )
      assert(result == expected)
    }

    it("should contain a logical lte") {
      val result = getForm("ql/binary/lte.ql")
      val expected = ASTRoot(
        ASTFormHeader(),
        ASTFormBody(
          List(
            ASTQuestion(ASTVarDecl(ASTBoolean())),
            ASTQuestion(ASTVarDecl(ASTBoolean())),

            ASTIfStatement(
              ASTBinary(ASTIdentifier(), ASTIdentifier(), ASTRelationalLTE()),
              List()
            )
          )
        )
      )
      assert(result == expected)
    }

    it("should contain a logical neq") {
      val result = getForm("ql/binary/ne.ql")
      val expected = ASTRoot(
        ASTFormHeader(),
        ASTFormBody(
          List(
            ASTQuestion(ASTVarDecl(ASTBoolean())),
            ASTQuestion(ASTVarDecl(ASTBoolean())),

            ASTIfStatement(
              ASTBinary(ASTIdentifier(), ASTIdentifier(), ASTRelationalNE()),
              List()
            )
          )
        )
      )
      assert(result == expected)
    }
  }
}
