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
        ASTFormHeader("taxOfficeExample"),
        ASTFormBody(
          List(
            ASTQuestion(
              "Did you sell a house in 2010?", 
              ASTVarDecl("hasSoldHouse", ASTBoolean(), null)
              )
            )
          )
        )
      assert(result == expected)
    }

    it("should contain two questions") {
      val result = getForm("ql/two_simple.ql")
      val expected = ASTRoot(
        ASTFormHeader("taxOfficeExample"),
        ASTFormBody(
          List(
            ASTQuestion(
              "Did you sell a house in 2010?", 
              ASTVarDecl("hasSoldHouse", ASTBoolean(), null)
            ),
            ASTQuestion(
              "Did you sell a house in 2011?", 
              ASTVarDecl("hasSoldHouse", ASTBoolean(), null)
            )
          )
        )
      )
      assert(result == expected)
    }
  }
}
