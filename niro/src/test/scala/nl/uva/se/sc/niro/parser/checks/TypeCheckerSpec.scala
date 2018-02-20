package nl.uva.se.sc.niro.parser.checks

import nl.uva.se.sc.niro.model.ql.AST._
import nl.uva.se.sc.niro.parser.TypeCheckException
import org.scalatest.FunSuite

class TypeCheckerSpec extends FunSuite {

  ignore("Fix exception handling in foreach of type checker before enabling this test...") {
    val form: Node = Form("TypeChecker", Seq(
      Conditional(VariableDefinition("invalidType", ExprType.String), Seq.empty, Seq.empty)
    ))
    val typeCheckError: TypeCheckException = intercept[TypeCheckException] {
      TypeChecker.checkTypes(form)
    }
    assert(typeCheckError.getMessage === "Conditional expression resolves not to boolean!")
  }
}
