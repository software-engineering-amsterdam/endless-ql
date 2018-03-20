import ql.models.ast._
import ql.validators._
import ql.spec.helpers._

import scala.io.Source

import org.scalatest.FunSpec
import org.scalatest.Matchers._
import org.scalatest.BeforeAndAfter

import org.antlr.v4.runtime._
import org.antlr.v4.runtime.tree._

class ValidatorHelperSpec extends FunSpec with BeforeAndAfter {
  val validator = ValidatorHelper

  describe("matching return type") {
    describe("Logical operands") {
      val operands = List(
        ASTLogicalCon(), ASTLogicalDis()
      )

      describe("should be yielding option boolean on boolean") {
        val types = List(ASTBoolean())

        it("should all be valid") {
          val expected = Some(ASTBoolean())

          for { op <- operands; nodeType <- types }
            yield assert(validator.matchReturnType(op, nodeType) == expected)
        }
      }

      describe("should be yielding none on string, integer and money") {
        val types = List(ASTString(), ASTInteger(), ASTMoney())

        it("should all be invalid") {
          val expected = None

          for { op <- operands; nodeType <- types }
            yield assert(validator.matchReturnType(op, nodeType) == expected)
        }
      }
    }

    describe("Relational operands") {
      val operands = List(
        ASTRelationalLT(), ASTRelationalLTE(), ASTRelationalGT(), ASTRelationalGTE()
      )

      describe("should be yielding option boolean on integer and money") {
        val types = List(ASTInteger(), ASTMoney())

        it("should all be valid") {
          val expected = Some(ASTBoolean())

          for { op <- operands; nodeType <- types }
            yield assert(validator.matchReturnType(op, nodeType) == expected)
        }
      }

      describe("should be yielding none on boolean and string") {
        val types = List(ASTString(), ASTBoolean())

        it("should all be invalid") {
          val expected = None

          for { op <- operands; nodeType <- types }
            yield assert(validator.matchReturnType(op, nodeType) == expected)
        }
      }
    }

    describe("Equality operands") {
      describe("should be yielding option boolean on boolean, string, integer and money") {
        val types = List(ASTBoolean(), ASTString(), ASTInteger(), ASTMoney())
        val operands = List(
          ASTNotEqualOp(),
          ASTEqualOp()
        )

        it("should all return be valid") {
          val expected = Some(ASTBoolean())

          for { nodeType <- types; op <- operands }
            yield assert(validator.matchReturnType(op, nodeType) == expected)
        }
      }
    }

    describe("Arithmetic operands") {
      val operands = List(
        ASTAdd(), ASTMin(), ASTDiv(), ASTMul()
      )

      describe("should be yielding option themselves on integer and money") {
        val types = List(ASTInteger(), ASTMoney())

        it("should all be valid") {
          for { op <- operands; nodeType <- types }
            yield assert(validator.matchReturnType(op, nodeType) == Some(nodeType))
        }
      }

      describe("should be yielding none on boolean and string") {
        val types = List(ASTBoolean(), ASTString())

        it("should all be invalid") {
          val expected = None

          for { op <- operands; nodeType <- types }
            yield assert(validator.matchReturnType(op, nodeType) == expected)
        }
      }
    }
  }
}
