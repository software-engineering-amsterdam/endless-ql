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

class ASTParser extends FunSpec with BeforeAndAfter {
  // maybe extract method to general helper class
  private def getForm(location:String): ASTNode = {
    return QlFormParser.parseFromURL(getClass.getResource(location))
  }

  describe("when parsing a form") {
    it("should contain a single question") {
      val result = getForm("ql/simple.ql")
      val expected = ASTRoot(
        ASTFormHeader(),
        ASTFormBody(
          List(
            ASTQuestion(ASTVarDecl(ASTBoolean()))
            )
          )
        )
      assert(result == expected)
    }

    it("should contain two questions") {
      val result = getForm("ql/two_simple.ql")
      val expected = ASTRoot(
        ASTFormHeader(),
        ASTFormBody(
          List(
            ASTQuestion(ASTVarDecl(ASTBoolean())),
            ASTQuestion(ASTVarDecl(ASTBoolean()))
          )
        )
      )
      assert(result == expected)
    }

    it("should contain conditional") {
      val result = getForm("ql/conditional.ql")
      val expected = ASTRoot(
        ASTFormHeader(),
        ASTFormBody(
          List(
            ASTQuestion(ASTVarDecl(ASTBoolean())),
            ASTQuestion(ASTVarDecl(ASTBoolean())),

            ASTIfStatement(
              ASTIdentifier(),
              List(
                ASTQuestion(ASTVarDecl(ASTMoney())),
                ASTQuestion(ASTVarDecl(ASTMoney())),

                ASTComputation(
                  ASTVarDecl(ASTMoney()),
                  ASTValAssign(
                    ASTBinary(
                      ASTIdentifier(),
                      ASTIdentifier(),
                      ASTMin()
                    )
                  )
                )
              )
            )
          )
        )
      )
      assert(result == expected)
    }

    it("should skip an extra set of brackets") {
      val result = getForm("ql/conditional_bracket.ql")
      val expected = ASTRoot(
        ASTFormHeader(),
        ASTFormBody(
          List(
            ASTQuestion(ASTVarDecl(ASTBoolean())),

            ASTIfStatement(
              ASTIdentifier(),
              List(
                ASTQuestion(ASTVarDecl(ASTMoney())),
              )
            )
          )
        )
      )
      assert(result == expected)
    }
  }
}
