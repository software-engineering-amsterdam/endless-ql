package nl.uva.se.sc.niro.parser.checks

import nl.uva.se.sc.niro.model.ql.AST._
import nl.uva.se.sc.niro.parser.CheckException
import org.scalatest.FunSuite

class TypeCheckerSpec extends FunSuite {

  test("should not allow string expression in conditional expression") {
    val form: Node = Form("TypeChecker", Seq(
      Conditional(VariableDefinition("invalidStringType", ExprType.String), Seq.empty, Seq.empty)
    ))

    val typeCheckError = intercept[CheckException] {
      TypeChecker.checkTypes(form)
    }

    assert(typeCheckError.getMessage === "Conditional expression resolves not to boolean!")
  }

  test("should not allow arithmetic expression in conditional expression") {
    val form: Node = Form("TypeChecker", Seq(
      Conditional(VariableDefinition("invalidIntegerType", ExprType.Integer), Seq.empty, Seq.empty)
    ))

    val typeCheckError = intercept[CheckException] {
      TypeChecker.checkTypes(form)
    }

    assert(typeCheckError.getMessage === "Conditional expression resolves not to boolean!")
  }
}
