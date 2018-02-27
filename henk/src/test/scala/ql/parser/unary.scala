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

class UnaryParser extends FunSpec with BeforeAndAfter {
  // maybe extract method to general helper class
  private def getForm(location:String): ASTNode = {
    return QlFormParser.parseFromURL(getClass.getResource(location))
  }

  describe("when parsing a form containing arithmetic tokens") {
    it("should contain one unary negation") {
      val result = getForm("ql/unary/not.ql")
      val expected = ASTRoot(
        ASTFormHeader(),
        ASTFormBody(
          List(
            ASTQuestion(ASTVarDecl(ASTBoolean())),
            ASTQuestion(ASTVarDecl(ASTBoolean())),

            ASTIfStatement(
              ASTUnary(ASTIdentifier(), ASTUnaryNot()),
              List()
            )
          )
        )
      )
      assert(result == expected)
    }

    it("should contain two unary negations") {
      val result = getForm("ql/unary/notnot.ql")
      val expected = ASTRoot(
        ASTFormHeader(),
        ASTFormBody(
          List(
            ASTQuestion(ASTVarDecl(ASTBoolean())),
            ASTQuestion(ASTVarDecl(ASTBoolean())),

            ASTIfStatement(
              ASTUnary(
                ASTUnary(ASTIdentifier(), ASTUnaryNot()),
                ASTUnaryNot()
              ),
              List()
            )
          )
        )
      )
      assert(result == expected)
    }

    it("should contain an unary min") {
      val result = getForm("ql/unary/min.ql")
      val expected = ASTRoot(
        ASTFormHeader(),
        ASTFormBody(
          List(
            ASTQuestion(ASTVarDecl(ASTBoolean())),
            ASTQuestion(ASTVarDecl(ASTBoolean())),

            ASTIfStatement(
              ASTUnary(ASTIdentifier(), ASTUnaryMin()),
              List()
            )
          )
        )
      )
      assert(result == expected)
    }
  }
}
