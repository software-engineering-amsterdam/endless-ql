import ql.models.ast._
import ql.collectors._
import ql.evaluators._
import ql.spec.helpers._

import org.scalatest.FunSpec
import org.scalatest.Matchers._

import org.scalatest.mockito.MockitoSugar
import org.mockito.Mockito._

class SymbolTableEvaluatorSpec extends FunSpec with MockitoSugar {
  val resourceDir = "ql/evaluators/symbol_table"
  val form = FormHelper.getRoot(getClass.getResource(s"${resourceDir}/simple.ql"))
  val evaluator = new SymbolTableEvaluator(form)

  // def reachableQuestions(location: String): List[Question] = {
    // val filename = s"${resourceDir}/${location}"
    // val form = FormHelper.getRoot(getClass.getResource(filename))
    // val statements = FormCollector.getStatements(form)
    // evaluator.getQuestions(form)
  // }

  // describe("conditionals containing no identifiers") {
    // it("should return 0") {
      // reachableQuestions("simple.ql") should have length (1)
    // }

    // it("conditionals containing expression that can be evaluated should be traversed") {
      // reachableQuestions("conditional_true.ql") should have length (2)
    // }

    // it("conditionals containing expression that can't be evaluated should not be returned") {
      // reachableQuestions("conditional_false.ql") should have length (1)
    // }

    // it("conditionals containing expression that can't be evaluated should continue in false branche") {
      // reachableQuestions("conditional_false_branch.ql") should have length (2)
    // }
  // }

  describe("cond containing binary expression") {
    describe("equality") {
      it("two true booleans should evaluate to true") {
        val checked = EqualOp(BooleanValue(false), BooleanValue(false)) 
        val result = evaluator.evaluateExpression(checked)
        assert(result == BooleanValue(true))
      }

      it("two integers should evaluate to true") {
        val checked = EqualOp(IntegerValue(1), IntegerValue(1)) 
        val result = evaluator.evaluateExpression(checked)
        assert(result == BooleanValue(true))
      }

      it("two different integers should evaluate to false") {
        val checked = EqualOp(IntegerValue(1), IntegerValue(2)) 
        val result = evaluator.evaluateExpression(checked)
        assert(result == BooleanValue(false))
      }

      it("two strings should evaluate to true") {
        val checked = EqualOp(StringValue("some"), StringValue("some")) 
        val result = evaluator.evaluateExpression(checked)
        assert(result == BooleanValue(true))
      }

      it("two different strings should evaluate to false") {
        val checked = EqualOp(StringValue("thing"), StringValue("some")) 
        val result = evaluator.evaluateExpression(checked)
        assert(result == BooleanValue(false))
      }

      it("any contained expressions are evaluated first") {
        val checked = EqualOp(IntegerValue(2), AddOp(IntegerValue(1), IntegerValue(1)))
        val result = evaluator.evaluateExpression(checked)
        assert(result == BooleanValue(true))
      }

      it("identifier should evaluate to default value of false") {
        val checked = Identifier("hasSoldHouse")
        val result = evaluator.evaluateExpression(checked)
        assert(result == BooleanValue(false))
      }

      it("when identifier exists in symbol table that value is used") {
        val checked = Identifier("hasSoldHouse")
        val mockedEvaluator = spy(evaluator)
        val st = collection.mutable.Map[Identifier, ExpressionValue](checked -> BooleanValue(true))
        when(mockedEvaluator.state).thenReturn(st)

        val result = mockedEvaluator.evaluateExpression(checked)
        assert(result == BooleanValue(true))
      }

      it("when identifier is used in a conditional") {
        val identifier = Identifier("hasSoldHouse")
        val checked = LogicalConOp(identifier, BooleanValue(true))
        val mockedEvaluator = spy(evaluator)
        val st = collection.mutable.Map[Identifier, ExpressionValue](identifier -> BooleanValue(true))
        when(mockedEvaluator.state).thenReturn(st)

        val result = mockedEvaluator.evaluateExpression(checked)
        assert(result == BooleanValue(true))
      }

    }

    describe("containing logical operand") {
      describe("conjunction") {
        it("two false booleans should evaluate to false") {
          val checked = LogicalConOp(BooleanValue(false), BooleanValue(false)) 
          val result = evaluator.evaluateExpression(checked)
          assert(result == BooleanValue(false))
        }

        it("when identifier is used in a conditional") {
          val identifier = Identifier("hasSoldHouse")
          val checked = RelationalLTOp(identifier, IntegerValue(2))
          val mockedEvaluator = spy(evaluator)
          val st = collection.mutable.Map[Identifier, ExpressionValue](identifier -> IntegerValue(1))
          when(mockedEvaluator.state).thenReturn(st)

          val result = mockedEvaluator.evaluateExpression(checked)
          assert(result == BooleanValue(true))
        }

        it("when identifier refering to a string is used in a conditional") {
          val identifier = Identifier("hasSoldHouse")
          val checked = LogicalConOp(BooleanValue(true), EqualOp(identifier, StringValue("some")))
          val mockedEvaluator = spy(evaluator)
          val st = collection.mutable.Map[Identifier, ExpressionValue](identifier -> StringValue("some"))
          when(mockedEvaluator.state).thenReturn(st)

          val result = mockedEvaluator.evaluateExpression(checked)
          assert(result == BooleanValue(true))
        }

        it("one false boolean should evaluate to false") {
          val assertionOne = evaluator.evaluateExpression(
            LogicalConOp(BooleanValue(true), BooleanValue(false))
          )
          val assertionTwo = evaluator.evaluateExpression(
            LogicalConOp(BooleanValue(false), BooleanValue(true))
          )
          assert(assertionOne == BooleanValue(false))
          assert(assertionTwo == BooleanValue(false))
        }

        it("two true booleans should evaluate to true") {
          val checked = LogicalConOp(BooleanValue(true), BooleanValue(true)) 
          val result = evaluator.evaluateExpression(checked)
          assert(result == BooleanValue(true))
        }

        it("nested consisting of only true booleans should evaluate to true") {
          val checked = LogicalConOp(BooleanValue(true), LogicalConOp(BooleanValue(true), BooleanValue(true)))
          val result = evaluator.evaluateExpression(checked)
          assert(result == BooleanValue(true))
        }
      }

      describe("disjunction") {
        it("two false booleans should evaluate to false") {
          val checked = LogicalDisOp(BooleanValue(false), BooleanValue(false)) 
          val result = evaluator.evaluateExpression(checked)
          assert(result == BooleanValue(false))
        }

        it("one true boolean should evaluate to true") {
          val assertionOne = evaluator.evaluateExpression(
            LogicalDisOp(BooleanValue(true), BooleanValue(false))
          )
          val assertionTwo = evaluator.evaluateExpression(
            LogicalDisOp(BooleanValue(false), BooleanValue(true))
          )
          assert(assertionOne == BooleanValue(true))
          assert(assertionTwo == BooleanValue(true))
        }

        it("two true booleans should evaluate to true") {
          val checked = LogicalDisOp(BooleanValue(true), BooleanValue(true)) 
          val result = evaluator.evaluateExpression(checked)
          assert(result == BooleanValue(true))
        }

        it("nested consisting of only true booleans should evaluate to true") {
          val checked = LogicalDisOp(BooleanValue(true), LogicalConOp(BooleanValue(true), BooleanValue(true)))
          val result = evaluator.evaluateExpression(checked)
          assert(result == BooleanValue(true))
        }
      }
    }

    describe("arithmetic") {
      val r = new scala.util.Random
      val nextR = () => -50 + r.nextInt(100)

      it("AddOperand should be equal to plus") {
        for(_ <- 1 to 100) {
          val lhs = nextR()
          val rhs = nextR()
          val checked = AddOp(IntegerValue(lhs), IntegerValue(rhs))
          val result = evaluator.evaluateExpression(checked)

          assert(result == IntegerValue(lhs+rhs))
        }
      }

      it("MinOperand should be equal to minus") {
        for(_ <- 1 to 100) {
          val lhs = nextR()
          val rhs = nextR()
          val checked = MinOp(IntegerValue(lhs), IntegerValue(rhs))
          val result = evaluator.evaluateExpression(checked)

          assert(result == IntegerValue(lhs-rhs))
        }
      }

      it("MulOperand should be equal to multiplication") {
        for(_ <- 1 to 100) {
          val lhs = nextR()
          val rhs = nextR()
          val checked = MinOp(IntegerValue(lhs), IntegerValue(rhs))
          val result = evaluator.evaluateExpression(checked)

          assert(result == IntegerValue(lhs-rhs))
        }
      }

      it("DivOp should be equal to division") {
        for(_ <- 1 to 100) {
          val lhs = nextR()
          val rhs = nextR()
          if(rhs != 0) {
            val checked = DivOp(IntegerValue(lhs), IntegerValue(rhs))
            val result = evaluator.evaluateExpression(checked)
            assert(result == IntegerValue(lhs/rhs))
          }
        }
      }

      it("should evaluate nested operands") {
        for(_ <- 1 to 100) {
          val lhs = nextR()
          val rhs = nextR()
          val o_lhs = nextR()
          val checked = AddOp(IntegerValue(o_lhs), AddOp(IntegerValue(lhs), IntegerValue(rhs)))
          val result = evaluator.evaluateExpression(checked)

          assert(result == IntegerValue(o_lhs+(lhs+rhs)))
        }
      }
    }

    describe("containing relational operand") {
      val r = new scala.util.Random
      val nextR = () => -50 + r.nextInt(100)

      it("whenever lhs < rhs true wil be returned") {
        for(_ <- 1 to 100) {
          val lhs = nextR()
          val rhs = nextR()
          val checked = RelationalLTOp(IntegerValue(lhs), IntegerValue(rhs))
          val result = evaluator.evaluateExpression(checked)

          if(lhs < rhs) {
            assert(result == BooleanValue(true))
          } else {
            assert(result == BooleanValue(false))
          }
        }
      }

      it("whenever lhs <= rhs true wil be returned") {
        for(_ <- 1 to 100) {
          val lhs = nextR()
          val rhs = nextR()
          val checked = RelationalLTEOp(IntegerValue(lhs), IntegerValue(rhs))
          val result = evaluator.evaluateExpression(checked)

          if(lhs <= rhs) {
            assert(result == BooleanValue(true))
          } else {
            assert(result == BooleanValue(false))
          }
        }
      }

      it("whenever lhs > rhs true wil be returned") {
        for(_ <- 1 to 100) {
          val lhs = nextR()
          val rhs = nextR()
          val checked = RelationalGTOp(IntegerValue(lhs), IntegerValue(rhs))
          val result = evaluator.evaluateExpression(checked)

          if(lhs > rhs) {
            assert(result == BooleanValue(true))
          } else {
            assert(result == BooleanValue(false))
          }
        }
      }

      it("whenever lhs >= rhs true wil be returned") {
        for(_ <- 1 to 100) {
          val lhs = nextR()
          val rhs = nextR()
          val checked = RelationalGTEOp(IntegerValue(lhs), IntegerValue(rhs))
          val result = evaluator.evaluateExpression(checked)

          if(lhs >= rhs) {
            assert(result == BooleanValue(true))
          } else {
            assert(result == BooleanValue(false))
          }
        }
      }
    }

    describe("unary expression") {
      describe("not") {
        it("should return false") {
          val checked = UnaryNotOp(BooleanValue(true))
          val result = evaluator.evaluateExpression(checked)
          assert(result == BooleanValue(false))
        }

        it("double should return true") {
          val checked = UnaryNotOp(UnaryNotOp(BooleanValue(true)))
          val result = evaluator.evaluateExpression(checked)
          assert(result == BooleanValue(true))
        }

        it("containing a conjunction should return false") {
          val checked = UnaryNotOp(LogicalConOp(BooleanValue(true), BooleanValue(true)))
          val result = evaluator.evaluateExpression(checked)
          assert(result == BooleanValue(false))
        }
      }

      describe("neg") {
        val r = new scala.util.Random
        val nextR = () => -500 + r.nextInt(1000)

        it("whenever lhs < rhs true wil be returned") {
          for(_ <- 1 to 100) {
            val i = nextR()
            val checked = UnaryMinOp(IntegerValue(i))
            val result = evaluator.evaluateExpression(checked)

            assert(result == IntegerValue(-i))
          }
        }

        it("should evaluate double nested negs") {
          val i = 10
          val checked = UnaryMinOp(UnaryMinOp(IntegerValue(i)))
          val result = evaluator.evaluateExpression(checked)

          assert(result == IntegerValue(i))
        }
      }
    }
  }
}
