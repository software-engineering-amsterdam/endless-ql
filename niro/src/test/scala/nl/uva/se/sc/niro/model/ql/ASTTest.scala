package nl.uva.se.sc.niro.model.ql

import nl.uva.se.sc.niro.model.ql.AST.ArithmeticOperator.MUL
import nl.uva.se.sc.niro.model.ql.AST.ExprType._
import nl.uva.se.sc.niro.model.ql.AST.UnaryOperators._
import nl.uva.se.sc.niro.model.ql.AST._
import org.scalatest.FunSuite

class ASTTest extends FunSuite {

  test("Just check the console for now") {
    val expected = Form("NewASTForm", Seq(
      Question("Did you sell your house this year?", VariableDefinition("hasSoldHouse", Bool)),
      Conditional(UnaryOperation(NEG, Variable("hasSoldHouse")),
        Seq(
          Question("What is the price you sold your house for?", VariableDefinition("priceOfHouse", Money))
        ),
        Seq(
          Question("Are you going to sell your house this year?", VariableDefinition("willSellHouse", Bool)),
          Question("Tax when selling house", VariableDeclaration("houseSellingTax", Money, Constant(Money, 1500.00)))
        )
      ),
      Conditional(BinaryOperation(MUL, Variable("priceOfHouse"), Variable("houseSellingTax")),
        Seq(
          Question("What is your lastname?", VariableDefinition("lastName", ExprType.String))
        ),
        Seq.empty)
    ))
    println(expected)
  }
}
