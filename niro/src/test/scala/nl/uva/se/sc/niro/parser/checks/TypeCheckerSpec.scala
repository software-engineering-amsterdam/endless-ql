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

  test("should not allow a string at the left hand side of an arithmetic expression") {
    val form: Node = Form("TypeChecker", Seq(
      Conditional(BinaryOperation(ArithmeticOperator.ADD, VariableDefinition("name", ExprType.String),VariableDefinition("invalidIntegerType", ExprType.Integer)), Seq.empty, Seq.empty)
    ))

    val typeCheckError = intercept[CheckException] {
      TypeChecker.checkTypes(form)
    }

    assert(typeCheckError.getMessage === "The left hand side of the arithmetic expression yields a string!")
  }

  test("should not allow a date at the left hand side of an arithmetic expression") {
    val form: Node = Form("TypeChecker", Seq(
      Conditional(BinaryOperation(ArithmeticOperator.ADD, VariableDefinition("date", ExprType.Date),VariableDefinition("invalidIntegerType", ExprType.Integer)), Seq.empty, Seq.empty)
    ))

    val typeCheckError = intercept[CheckException] {
      TypeChecker.checkTypes(form)
    }

    assert(typeCheckError.getMessage === "The left hand side of the arithmetic expression yields a date!")
  }

  test("should not allow a boolean at the left hand side of an arithmetic expression") {
    val form: Node = Form("TypeChecker", Seq(
      Conditional(BinaryOperation(ArithmeticOperator.ADD, VariableDefinition("name", ExprType.Bool),VariableDefinition("invalidIntegerType", ExprType.Integer)), Seq.empty, Seq.empty)
    ))

    val typeCheckError = intercept[CheckException] {
      TypeChecker.checkTypes(form)
    }

    assert(typeCheckError.getMessage === "The left hand side of the arithmetic expression yields a boolean!")
  }
}
