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

class ConditionTypeSpec extends FunSpec with BeforeAndAfter {
  // maybe extract method to general helper class
  private def getForm(location: String): ASTNode = {
    return QlFormParser.parseFromURL(getClass.getResource(location))
  }

  describe("when typechecking a valid form") {
    val filename = "ql/typechecking/conditions/simple.ql"
    val typechecker = new TypeChecker(getForm(filename))

    it("check method should return true") {
      assert(typechecker.check() == true)
    }

    it("checkVarDecls method should not throw an exception") {
      noException should be thrownBy typechecker.checkVarDecls
    }
  }
}
