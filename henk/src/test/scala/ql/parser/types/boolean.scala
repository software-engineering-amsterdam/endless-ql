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

class ParserTypeBooleanSpec extends FunSpec with BeforeAndAfter {
  // maybe extract method to general helper class
  private def getFlattenedForm(location: String): List[ASTNode] = {
    val form = QlFormParser.parseFromURL(getClass.getResource(location))
    ASTCollector.flattenNT(form)
  }

  describe("parsing a form containg a true boolean literal") {
    it("should contain an ASTBooleanValue") {
      val result = getFlattenedForm("ql/parser/types/boolean.ql")
      val expected = ASTValAssign(ASTBooleanValue(true))

      assert(result.filter(x => x == expected).size == 1)
    }
  }

  describe("parsing a form containg a false boolean literal") {
    it("should contain an ASTBooleanValue") {
      val result = getFlattenedForm("ql/parser/types/false.ql")
      val expected = ASTValAssign(ASTBooleanValue(false))

      assert(result.filter(x => x == expected).size == 1)
    }
  }
}
