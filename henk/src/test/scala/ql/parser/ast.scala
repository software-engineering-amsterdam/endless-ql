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

class ASTParser extends FunSpec with BeforeAndAfter {
  // maybe extract method to general helper class
  private def getForm(location: String): ASTNode = {
    return QlFormParser.parseFromURL(getClass.getResource(location))
  }

  private def getFlattenedForm(location: String): List[ASTNode] = {
    val form = QlFormParser.parseFromURL(getClass.getResource(location))
    QlFormParser.flattenNT(form)
  }

  describe("when parsing a form") {
    it("should contain a single question") {
      val result = getFlattenedForm("ql/simple.ql")
      val expected = ASTQuestion(ASTVarDecl(ASTBoolean(), ASTIdentifier("hasSoldHouse")),
                                 "Did you sell a house in 2010?")

      assert(result.filter(x => x == expected).size == 1)
    }

    it("should contain two questions") {
      val result = getFlattenedForm("ql/two_statements_simple.ql")
      val q1 = ASTQuestion(ASTVarDecl(ASTBoolean(), ASTIdentifier("hasSoldHouse")),
                           "Did you sell a house in 2010?")
      val q2 = ASTQuestion(ASTVarDecl(ASTBoolean(), ASTIdentifier("hasSoldHouse")),
                           "Did you sell a house in 2011?")

      assert(result.filter(x => x == q1 || x == q2).size == 2)
    }

    it("should contain conditional") {
      val result = getFlattenedForm("ql/conditional.ql")
      val expected = ASTIfStatement(
        ASTIdentifier("hasSoldHouse"),
        List(
          ASTQuestion(ASTVarDecl(ASTMoney(), ASTIdentifier("sellingPrice")),
                      "What was the selling price?"),
          ASTQuestion(ASTVarDecl(ASTMoney(), ASTIdentifier("privateDebt")),
                      "Private debts for the sold house:"),
          ASTComputation(
            ASTVarDecl(ASTMoney(), ASTIdentifier("valueResidue")),
            ASTValAssign(
              ASTBinary(
                ASTIdentifier("sellingPrice"),
                ASTIdentifier("privateDebt"),
                ASTMin()
              )
            ),
            "Value residue:"
          )
        )
      )
      assert(result.filter(x => x == expected).size == 1)
    }

    it("should skip an extra set of brackets") {
      val result = getFlattenedForm("ql/conditional_bracket.ql")
      val expected = ASTIfStatement(
        ASTIdentifier("hasSoldHouse"),
        List(
          ASTQuestion(ASTVarDecl(ASTMoney(), ASTIdentifier("sellingPrice")),
                      "What was the selling price?")
        )
      )
      assert(result.filter(x => x == expected).size == 1)
    }
  }
}
