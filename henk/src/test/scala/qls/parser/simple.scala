import grammar._

import ql.models._
import qls.listeners._
import ql.visitors._

import scala.io.Source

import org.scalatest.FunSpec
import org.scalatest.Matchers._
import org.scalatest.BeforeAndAfter

import org.antlr.v4.runtime._
import org.antlr.v4.runtime.tree._

class SimpleQLSGrammarParserSpec extends FunSpec with BeforeAndAfter {

  describe("when parsing a basic form") {
    var listener: CountNodesListener = null

    before {
      val source = Source.fromURL(getClass.getResource("qls/parser/simple.qls"))
      val sourcedLines = source.mkString
      source.close

      listener = new CountNodesListener()
      val parser = Main.getQLSParser(sourcedLines)
      val tree = parser.root()

      val paul = new ParseTreeWalker()
      paul.walk(listener, tree)
    }

    it("'root' node count should be 1") {
      assert(listener.node_count("root") == 1)
    }

    it("'page' node count should be 1") {
      assert(listener.node_count("page") == 1)
    }

    it("'section' node count should be 2") {
      assert(listener.node_count("section") == 2)
    }

    it("'question' node count should be 2") {
      assert(listener.node_count("question") == 2)
    }

    it("'identifier' node count should be 4") {
      assert(listener.node_count("identifier") == 4)
    }
  }
}
