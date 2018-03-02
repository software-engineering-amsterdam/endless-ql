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

class TypeCheckerSpec extends FunSpec with BeforeAndAfter {
  // maybe extract method to general helper class
  private def getForm(location: String): ASTNode = {
    return QlFormParser.parseFromURL(getClass.getResource(location))
  }

  describe("when typechecking a valid form") {
    val filename = "ql/typechecking/simple.ql"
    val typechecker = new TypeChecker(getForm(filename))

    it("check method should return true") {
      assert(typechecker.check() == true)
    }

    it("checkVarDecls method should not throw an exception") {
      noException should be thrownBy typechecker.checkVarDecls
    }
  }

  describe("when typechecking a form with a single undeclared identifier") {
    val filename = "ql/typechecking/single_undeclared_identifier.ql"
    val typechecker = new TypeChecker(getForm(filename))

    it("check method should return true") {
      assert(typechecker.check() == false)
    }

    it("checkVarDecls method should throw an IdentifierNotDeclared exception") {
      assertThrows[IdentifierNotDeclared] {
        typechecker.checkVarDecls()
      }
    }

    it("typechecker should contain appropriate error message") {
      val expectedMessage =
        "Identifier with name 'undeclaredIdentifier' is not declared!"

      assert(typechecker.errorMessage == expectedMessage)
    }
  }

  describe("when typechecking a form with multiple undeclared identifiers") {
    val filename = "ql/typechecking/multiple_undeclared_identifiers.ql"
    val typechecker = new TypeChecker(getForm(filename))

    it("check method should return true") {
      assert(typechecker.check() == false)
    }

    it("checkVarDecls should throw an IdentifierNotDeclared exception") {
      assertThrows[IdentifierNotDeclared] {
        typechecker.checkVarDecls()
      }
    }

    it("error message should contain name first found undeclared identifier") {
      val expectedMessage =
        "Identifier with name 'firstUndeclaredIdentifier' is not declared!"

      assert(typechecker.errorMessage == expectedMessage)
    }
  }
}
