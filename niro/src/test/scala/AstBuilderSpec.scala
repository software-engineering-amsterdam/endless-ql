import java.io.IOException

import model.Ast.AnswerType.{ BooleanAnswerType, StringAnswerType }
import model.Ast.Expression.Operator.ArithmOp.{ Add, Div, Mul, Sub }
import model.Ast.Expression.Operator.CompOp.{ Gt, Ne }
import model.Ast.Expression.Operator.LogicalOp.And
import model.Ast.Expression.Operator.UnaryOp.{ Min, Negate }
import model.Ast.Expression.{ BoolConst, Ident, IntConst }
import model.Ast._
import org.antlr.v4.runtime.{ CharStream, CharStreams }
import org.scalatest.FunSuite
import parser.QLFormParser

class AstBuilderSpec extends FunSuite {
  @throws[IOException]
  protected def toCharStream(fileName: String): CharStream = CharStreams.fromStream(getClass.getResourceAsStream(fileName))

  test("simple ql") {

    val actual: QLForm = QLFormParser.parse(toCharStream("/positive/simple.ql"))

    val expected: QLForm =
      QLForm(
        formName = "Box1HouseOwning",
        statements = List(
          Question(
            id = Ident("hasSoldHouse"),
            label = "Did you sell a house in 2010?",
            answerType = BooleanAnswerType
          ),
          Question(
            id = Ident("hasBoughtHouse"),
            label = "Did you by a house in 2010?",
            answerType = BooleanAnswerType
          )
        )
      )

    assert(actual == expected)
  }

  test("simple-conditional-if") {

    val actual: QLForm = QLFormParser.parse(toCharStream("/positive/simple-conditional-if.ql"))

    val expected: QLForm =
      QLForm(
        formName = "ConditionQuestions",
        statements = List(
          Question(
            id = Ident("firstName"),
            label = "What is your first name?",
            answerType = StringAnswerType
          ),
          Conditional(
            condition = BoolConst(true),
            ifStatements = List(
              Question(
                id = Ident("middleName"),
                label = "What is your middle name?",
                answerType = StringAnswerType
              )
            ),
            elseStatements = List.empty
          )
        )
      )

    assert(actual == expected)
  }

  test("/positive/not-conditional-if.ql") {
    val actual: QLForm = QLFormParser.parse(toCharStream("/positive/not-conditional-if.ql"))
    val expected = QLForm(
      formName = "ConditionQuestions",
      statements = List(
        Question(id = Ident("firstName"), label = "What is your first name?", answerType = StringAnswerType),
        Conditional(
          condition = Negate(BoolConst(true)),
          ifStatements = List(
            Question(Ident("middleName"), "What is your middle name?", StringAnswerType)
          ),
          elseStatements = List.empty
        )
      )
    )
    assert(actual == expected)
  }

  test("/positive/simple-conditional-if-else.ql") {
    val actual: QLForm = QLFormParser.parse(toCharStream("/positive/simple-conditional-if-else.ql"))
    val expected = QLForm(
      formName = "ConditionQuestions",
      statements = List(
        Question(id = Ident("firstName"), "What is your first name?", answerType = StringAnswerType),
        Conditional(
          condition = BoolConst(true),
          ifStatements = List(
            Question(id = Ident("middleName"), label = "What is your middle name?", answerType = StringAnswerType)
          ),
          elseStatements = List(
            Question(id = Ident("lastName"), label = "What is your last name?", answerType = StringAnswerType),
            Question(id = Ident("lastName"), label = "What is your last name?", answerType = StringAnswerType)
          )
        )
      )
    )
    assert(actual == expected)
  }

  test("/positive/nested-conditional.ql") {
    val actual: QLForm = QLFormParser.parse(toCharStream("/positive/nested-conditional.ql"))
    val expected = QLForm(
      formName = "QonditionQuestions",
      statements = List(
        Question(id = Ident("firstName"), label = "What is your first name?", answerType = StringAnswerType),
        Conditional(
          condition = BoolConst(true),
          ifStatements = List(
            Question(Ident("lastName"), "What is your last name?", StringAnswerType)
          ),
          elseStatements = List(
            Question(Ident("middleName"), "What is your middle name?", StringAnswerType),
            Conditional(
              condition = BoolConst(false),
              ifStatements = List(
                Question(Ident("lastName"), "What is your last name?", StringAnswerType)
              ),
              elseStatements = List(
                Question(Ident("middleName"), "What is your middle name?", StringAnswerType)
              )
            )
          )
        )
      )
    )

    assert(actual == expected)
  }

  test("/positive/expression-conditional-if.ql") {
    val actual: QLForm = QLFormParser.parse(toCharStream("/positive/expression-conditional-if.ql"))
    val expected = QLForm(
      formName = "QonditionQuestions",
      statements = List(
        Question(id = Ident("firstName"), label = "What is your first name?", answerType = StringAnswerType),
        Question(id = Ident("lastName"), label = "What is your last name?", answerType = StringAnswerType),
        Question(id = Ident("middleName"), label = "Do you have a middle name?", answerType = BooleanAnswerType),
        Conditional(
          condition = And(Ne(Ident("lastName"), Ident("firstName")), Gt(Ident("a"), Min(IntConst(10)))),
          ifStatements = List(
            Question(id = Ident("lastName"), label = "What is your last name?", answerType = StringAnswerType)
          ),
          elseStatements = List.empty
        )
      )
    )
    assert(actual == expected)

  }

  test("/positive/expression-conditional-if-else.ql") {
    val actual: QLForm = QLFormParser.parse(toCharStream("/positive/expression-conditional-if-else.ql"))
    val expected = QLForm(
      formName = "QonditionQuestions",
      statements = List(
        Question(id = Ident("firstName"), label = "What is your first name?", answerType = StringAnswerType),
        Question(id = Ident("lastName"), label = "What is your last name?", answerType = StringAnswerType),
        Question(id = Ident("middleName"), label = "Do you have a middle name?", answerType = BooleanAnswerType),
        Conditional(
          condition = And(Ne(Ident("lastName"), Ident("firstName")), Gt(Ident("a"), Min(IntConst(10)))),
          ifStatements = List(
            Question(Ident("lastName"), "What is your last name?", StringAnswerType),
            Question(Ident("lastName"), "What is your last name?", StringAnswerType)
          ),
          elseStatements = List(
            Question(Ident("middleName"), "Do you have a middle name?", BooleanAnswerType),
            Question(Ident("middleName"), "Do you have a middle name?", BooleanAnswerType)
          )
        )
      )
    )
    assert(actual == expected)
  }

  test("/positive/nested-expression.ql") {
    val actual: QLForm = QLFormParser.parse(toCharStream("/positive/nested-expression.ql"))
    val expected = QLForm(
      formName = "Box1HouseOwning",
      statements = List(
        Question(id = Ident("hasSoldHouse"), label = "Did you sell a house in 2010?", answerType = BooleanAnswerType),
        Conditional(
          condition = Div(Add(Mul(Sub(IntConst(10000), Ident("hasSoldHouse")), IntConst(42)), IntConst(23)), IntConst(54)),
          ifStatements = List(
            Question(Ident("asd"), "asd", BooleanAnswerType)
          ),
          elseStatements = List.empty
        )
      )
    )
    assert(actual == expected)
  }
}
