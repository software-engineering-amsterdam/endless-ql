import ql.grammar._
import ql.listeners._

import org.scalatest.FunSpec
import org.scalatest.Matchers._
import org.scalatest.BeforeAndAfter

import org.antlr.v4.runtime._
import org.antlr.v4.runtime.tree._

class GrammarSpec extends FunSpec with BeforeAndAfter {

  describe("when parsing a basic form") {
    var listener: CountNodesListener = null

    before {
      val form = """
        form taxOfficeExample {
          "Did you sell a house in 2010?"
          hasSoldHouse: boolean
        }
      """

      listener = new CountNodesListener()
      val parser = Main.getParser(form)
      val tree = parser.root()

      val walker = new ParseTreeWalker()
      walker.walk(listener, tree)
    }

    it("'question' node count should be 1") {
      assert(listener.node_count("questions") == 1)
    }

    it("'root' node count should be 1") {
      assert(listener.node_count("root") == 1)
    }

    it("'typeDecl' node count should be 1") {
      assert(listener.node_count("typeDecl") == 1)
    }

    it("'form' node count should be 1") {
      assert(listener.node_count("form") == 1)
    }

    it("'formHeader' node count should be 1") {
      assert(listener.node_count("formHeader") == 1)
    }

    it("'varDecl' node count should be 1") {
      assert(listener.node_count("varDecl") == 1)
    }
  }
}

class GrammarOneSpec extends FunSpec with BeforeAndAfter {
  describe("when parsing a form with a conditional") {
    var listener: CountNodesListener = null

    before {
      val form = """
        form taxOfficeExample {
          "Did you sell a house in 2010?"
          hasSoldHouse: boolean
          "Did you buy a house in 2010?"
          hasSoldHouse: boolean

          if (hasSoldHouse) {
            "What was the selling price?"
              sellingPrice: money
            "Private debts for the sold house:"
              privateDebt: money
            "Value residue:"
              valueResidue: money =
                (sellingPrice - privateDebt)
          }
        }
      """

      listener = new CountNodesListener()
      val parser = Main.getParser(form)
      val tree = parser.root()

      val walker = new ParseTreeWalker()
      walker.walk(listener, tree)
    }

    it("'question' node count should be 5") {
      assert(listener.node_count("questions") == 5)
    }

    it("'root' node count should be 1") {
      assert(listener.node_count("root") == 1)
    }

    it("'typeDecl' node count should be 5") {
      assert(listener.node_count("typeDecl") == 5)
    }

    it("'form' node count should be 1") {
      assert(listener.node_count("form") == 1)
    }

    it("'formHeader' node count should be 1") {
      assert(listener.node_count("formHeader") == 1)
    }

    it("'varDecl' node count should be 5") {
      assert(listener.node_count("varDecl") == 5)
    }

    it("'ifStmt' node count should be 1") {
      assert(listener.node_count("ifStmt") == 1)
    }

    it("'varDecl' node count should be 1") {
      assert(listener.node_count("ifBody") == 1)
    }

    it("'binOp' node count should be 1") {
      assert(listener.node_count("binOp") == 1)
    }
  }
}
