import grammar._

import ql.models.ast._
import ql.visitors._
import ql.parsers._

import scala.io.Source

import org.scalatest.FunSpec
import org.scalatest.Matchers._
import org.scalatest.BeforeAndAfter

import org.antlr.v4.runtime._
import org.antlr.v4.runtime.tree._

class ArithmeticParserSpec extends FunSpec with BeforeAndAfter {
  // maybe extract method to general helper class
  private def getForm(location: String): ASTNode = {
    return QlFormParser.parseFromURL(getClass.getResource(location))
  }

  private def getFlattenedForm(location: String): List[ASTNode] = {
    val form = QlFormParser.parseFromURL(getClass.getResource(location))
    ASTCollector.flattenNT(form)
  }

  describe("when parsing a conditional containing arithmetic binop") {
    it("should contain a single question") {
      val result = getFlattenedForm("ql/arithmetic/simple_conditional.ql")
      val expected = ASTBinary(ASTIntegerValue(1), ASTIntegerValue(1), ASTAdd())

      assert(result.filter(x => x == expected).size == 1)
    }
  }
}
