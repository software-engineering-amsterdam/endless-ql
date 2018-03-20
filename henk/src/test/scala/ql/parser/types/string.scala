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

class ParserTypeStringSpec extends FunSpec with BeforeAndAfter {
  // maybe extract method to general helper class
  private def getFlattenedForm(location: String): List[ASTNode] = {
    val form = QlFormParser.parseFromURL(getClass.getResource(location))
    ASTCollector.flattenNT(form)
  }

  describe("parsing a form containg a string literal") {
    it("should contain an ASTStringValue") {
      val result = getFlattenedForm("ql/parser/types/string.ql")
      val expected = ASTValAssign(ASTStringValue("Amsterdam"))

      assert(result.filter(x => x == expected).size == 1)
    }
  }
}
