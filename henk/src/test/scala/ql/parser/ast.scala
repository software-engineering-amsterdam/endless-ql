import ql.models.ast._
import ql.spec.helpers._

import org.scalatest.FunSpec
import org.scalatest.Matchers._

class QLASTParserSpec extends FunSpec {
  describe("when parsing a form") {
    it("should contain a single question") {
      val questions =
        FormHelper.getQuestions(getClass.getResource("ql/simple.ql"))
      val expected =
        Question(VarDecl(BooleanType(), Identifier("hasSoldHouse")),
                 "Did you sell a house in 2010?")

      questions should contain(expected)
    }
  }

  it("should contain two questions") {
    val questions = FormHelper.getQuestions(
      getClass.getResource("ql/two_statements_simple.ql"))
    val q1 = Question(VarDecl(BooleanType(), Identifier("hasSoldHouse")),
                      "Did you sell a house in 2010?")
    val q2 = Question(VarDecl(BooleanType(), Identifier("hasSoldHouse")),
                      "Did you sell a house in 2011?")

    questions should contain(q1)
    questions should contain(q2)
  }

  it("should contain conditional") {
    val ifStmt =
      FormHelper.getIfStatements(getClass.getResource("ql/conditional.ql"))
    val expected = IfStatement(
      Identifier("hasSoldHouse"),
      List(
        Question(VarDecl(MoneyType(), Identifier("sellingPrice")),
                 "What was the selling price?"),
        Question(VarDecl(MoneyType(), Identifier("privateDebt")),
                 "Private debts for the sold house:"),
        Computation(
          VarDecl(MoneyType(), Identifier("valueResidue")),
          ValAssign(
            MinOp(
              Identifier("sellingPrice"),
              Identifier("privateDebt")
            )
          ),
          "Value residue:"
        )
      )
    )
    ifStmt should contain(expected)
  }

  it("should skip an extra set of brackets") {
    val ifStmt = FormHelper.getIfStatements(
      getClass.getResource("ql/conditional_bracket.ql"))
    val expected = IfStatement(
      Identifier("hasSoldHouse"),
      List(
        Question(VarDecl(MoneyType(), Identifier("sellingPrice")),
                 "What was the selling price?")
      )
    )
    ifStmt should contain(expected)
  }
}
