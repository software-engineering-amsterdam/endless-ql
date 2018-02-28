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

class UnaryParser extends FunSpec with BeforeAndAfter {
  // maybe extract method to general helper class
  private def getFlattenedForm(location: String): List[ASTNode] = {
    val form = QlFormParser.parseFromURL(getClass.getResource(location))
    QlFormParser.flattenNT(form)
  }

  describe("when parsing a form containing arithmetic tokens") {
    it("should contain one unary negation") {
      val result = getFlattenedForm("ql/unary/not.ql")
      val expected = ASTUnary(ASTIdentifier(), ASTUnaryNot())

      assert(result.filter(x => x == expected).size == 1)
    }

    it("should contain two unary negations") {
      val result = getFlattenedForm("ql/unary/notnot.ql")
      val expected = ASTUnary(
        ASTUnary(ASTIdentifier(), ASTUnaryNot()),
        ASTUnaryNot()
      )

      assert(result.filter(x => x == expected).size == 1)
    }

    it("should contain an unary min") {
      val result = getFlattenedForm("ql/unary/min.ql")
      val expected = ASTUnary(ASTIdentifier(), ASTUnaryMin())

      assert(result.filter(x => x == expected).size == 1)
    }
  }
}
