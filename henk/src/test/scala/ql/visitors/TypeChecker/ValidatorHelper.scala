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
  val stubbed_ast = Root(FormHeader(Identifier("stub")), FormBody(List()))

  describe("matching return type") {
    describe("Logical operands") {
      val operands = List(
        LogicalConOp(_,_), LogicalDisOp(_,_)
      )

      describe("should be yielding option boolean on boolean") {
        val values = List(BooleanValue(false), BooleanValue(true))

        it("should all be valid") {
          val expected = Some(BooleanType)

          for (op <- operands; lhs <- values; rhs <- values) {
            val evaluant = op(lhs, rhs)
            assert(validator.infereBinary(evaluant, stubbed_ast) == expected)
          }
        }
      }

      describe("should be yielding None on String and Integer") {
        val values = List(StringValue("some"), IntegerValue(1))

        it("should all be invalid") {
          val expected = None

          for (op <- operands; lhs <- values; rhs <- values) {
            val evaluant = op(lhs, rhs)
            assert(validator.infereBinary(evaluant, stubbed_ast) == expected)
          }
        }
      }
    }

    describe("Relational operands") {
      val operands = List(
        RelationalLTOp(_,_),
        RelationalLTEOp(_,_),
        RelationalGTOp(_,_),
        RelationalGTEOp(_,_)
      )

      describe("should be yielding option boolean on integer and money") {
        val values = List(IntegerValue(1))

        it("should all be valid") {
          val expected = Some(BooleanType)

          for(op <- operands; rhs <- values; lhs <- values) {
            val evaluant = op(lhs, rhs)
            assert(validator.infereBinary(evaluant, stubbed_ast) == expected)
          }
        }
      }

      describe("should be yielding none on boolean and string") {
        val values = List(StringValue("some"), BooleanValue(false), BooleanValue(true))

        it("should all be invalid") {
          val expected = None

          for(op <- operands; rhs <- values; lhs <- values) {
            val evaluant = op(lhs, rhs)
            assert(validator.infereBinary(evaluant, stubbed_ast) == expected)
          }
        }
      }
    }

    describe("Equality operands") {
      describe("should be yielding option boolean on boolean, string, integer and money") {
        val operands = List(NotEqualOp(_,_), EqualOp(_,_))
        val values = List(StringValue("some"), BooleanValue(false), BooleanValue(true), IntegerValue(1))

        it("should all return Boolean valid") {
          val expected = Some(BooleanType)

          for(op <- operands; value <- values) {
            val evaluant = op(value, value)
            assert(validator.infereBinary(evaluant, stubbed_ast) == expected)
          }
        }
      }
    }

    describe("Arithmetic operands") {
      val operands = List(
        AddOp(_,_), MinOp(_,_), DivOp(_,_), MulOp(_,_)
      )

      describe("should be yielding option Integer") {
        val values = List(IntegerValue(1))

        it("should all be valid") {
          val expected = Some(IntegerType)

          for(op <- operands; value <- values) {
            val evaluant = op(value, value)
            assert(validator.infereBinary(evaluant, stubbed_ast) == expected)
          }
        }
      }

      describe("should be yielding None on boolean and string") {
        val values = List(BooleanValue(false), BooleanValue(true), StringValue("some"))

        it("should all be invalid") {
          val expected = None

          for(op <- operands; lhs <- values; rhs <- values) {
            val evaluant = op(lhs, rhs)
            assert(validator.infereBinary(evaluant, stubbed_ast) == expected)
          }
        }
      }
    }
  }
}
