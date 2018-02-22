import java.io.IOException

import nl.uva.se.sc.niro.model.Expressions.{ BinaryOperation, Reference, UnaryOperation }
import nl.uva.se.sc.niro.model.Expressions.answers._
import nl.uva.se.sc.niro.model._
import nl.uva.se.sc.niro.parser.QLFormParser
import org.antlr.v4.runtime.{ CharStream, CharStreams }
import org.scalatest.FunSuite

class AstBuilderSpec extends FunSuite {
  @throws[IOException]
  private def toCharStream(filePath: String): CharStream = CharStreams.fromStream(getClass.getResourceAsStream(filePath))
  private def generateQLForm(filePath: String): QLForm = QLFormParser.parse(toCharStream(filePath))

  test("should parse simple-ql to the correct AST") {
    val actual: QLForm = generateQLForm("/positive/simple.ql")

    val expected: QLForm =
      QLForm(
        formName = "Box1HouseOwning",
        statements = List(
          Question(
            id = "hasSoldHouse",
            label = "Did you sell a house in 2010?",
            answer = BooleanAnswer(None)
          ),
          Question(
            id = "hasBoughtHouse",
            label = "Did you by a house in 2010?",
            answer = BooleanAnswer(None)
          )
        )
      )

    assert(actual == expected)
  }

  test("should parse simple-conditional-if to the correct AST") {
    val actual: QLForm = generateQLForm("/positive/simple-conditional-if.ql")

    val expected: QLForm =
      QLForm(
        formName = "ConditionQuestions",
        statements = List(
          Question(
            id = "firstName",
            label = "What is your first name?",
            answer = StringAnswer(None)
          ),
          Conditional(
            condition = BooleanAnswer(Some(true)),
            ifStatements = List(
              Question(
                id = "middleName",
                label = "What is your middle name?",
                answer = StringAnswer(None)
              )
            ),
            elseStatements = List.empty
          )
        )
      )

    assert(actual == expected)
  }

  test("should parse not-conditional-if to the correct AST") {
    val actual: QLForm = generateQLForm("/positive/not-conditional-if.ql")
    val expected = QLForm(
      formName = "ConditionQuestions",
      statements = List(
        Question(id = "firstName", label = "What is your first name?", answer = StringAnswer(None)),
        Conditional(
          condition = UnaryOperation(Neg, BooleanAnswer(Some(true))),
          ifStatements = List(
            Question("middleName", "What is your middle name?", StringAnswer(None))
          ),
          elseStatements = List.empty
        )
      )
    )

    assert(actual == expected)
  }

  test("should parse simple-conditional-if-else to the correct AST") {
    val actual: QLForm = generateQLForm("/positive/simple-conditional-if-else.ql")
    val expected = QLForm(
      formName = "ConditionQuestions",
      statements = List(
        Question(id = "firstName", "What is your first name?", answer = StringAnswer(None)),
        Conditional(
          condition = BooleanAnswer(Some(true)),
          ifStatements = List(
            Question(id = "middleName", label = "What is your middle name?", answer = StringAnswer(None))
          ),
          elseStatements = List(
            Question(id = "lastName", label = "What is your last name?", answer = StringAnswer(None)),
            Question(id = "lastName", label = "What is your last name?", answer = StringAnswer(None))
          )
        )
      )
    )

    assert(actual == expected)
  }

  test("should parse nested-conditional to the correct AST") {
    val actual: QLForm = generateQLForm("/positive/nested-conditional.ql")
    val expected = QLForm(
      formName = "QonditionQuestions",
      statements = List(
        Question(id = "firstName", label = "What is your first name?", answer = StringAnswer(None)),
        Conditional(
          condition = BooleanAnswer(Some(true)),
          ifStatements = List(
            Question("lastName", "What is your last name?",answer = StringAnswer(None))
          ),
          elseStatements = List(
            Question("middleName", "What is your middle name?", answer = StringAnswer(None)),
            Conditional(
              condition = BooleanAnswer(Some(false)),
              ifStatements = List(
                Question("lastName", "What is your last name?", answer = StringAnswer(None))
              ),
              elseStatements = List(
                Question("middleName", "What is your middle name?", answer = StringAnswer(None))
              )
            )
          )
        )
      )
    )

    assert(actual == expected)
  }

  test("should parse expression-conditional-if to the correct AST") {
    val actual: QLForm = generateQLForm("/positive/expression-conditional-if.ql")
    val expected = QLForm(
      formName = "QonditionQuestions",
      statements = List(
        Question(id = "firstName", label = "What is your first name?", answer = StringAnswer(None)),
        Question(id = "lastName", label = "What is your last name?", answer = StringAnswer(None)),
        Question(id = "middleName", label = "Do you have a middle name?", answer = BooleanAnswer(None)),
        Conditional(
          condition = BinaryOperation(And, BinaryOperation(Ne, Reference("lastName"), Reference("firstName")), BinaryOperation(Gt, Reference("a"), UnaryOperation(Min, IntAnswer(Some(10))))),
          ifStatements = List(
            Question(id = "lastName", label = "What is your last name?", answer = StringAnswer(None))
          ),
          elseStatements = List.empty
        )
      )
    )

    assert(actual == expected)
  }

  test("should parse expression-conditional-if-else to the correct AST") {
    val actual: QLForm = generateQLForm("/positive/expression-conditional-if-else.ql")
    val expected = QLForm(
      formName = "QonditionQuestions",
      statements = List(
        Question(id = "firstName", label = "What is your first name?", answer = StringAnswer(None)),
        Question(id = "lastName", label = "What is your last name?", answer = StringAnswer(None)),
        Question(id = "middleName", label = "Do you have a middle name?", answer = BooleanAnswer(None)),
        Conditional(
          BinaryOperation(
            binaryOperator = And,
            left = BinaryOperation(
              binaryOperator = Ne,
              left = Reference("lastName"),
              right = Reference("firstName")
            ),
            right = BinaryOperation(
              binaryOperator = Gt,
              left = Reference("a"),
              right = UnaryOperation(
                unaryOperator = Min,
                left = IntAnswer(Some(10))
              )
            )
          ),
          List(
            Question(id = "lastName", label = "What is your last name?", answer = StringAnswer(None)),
            Question(id = "lastName", label = "What is your last name?", answer = StringAnswer(None))),
          List(
            Question(id = "middleName", label = "Do you have a middle name?", answer = BooleanAnswer(None)),
            Question(id = "middleName", label = "Do you have a middle name?", answer = BooleanAnswer(None))
          )
        )
      )
    )

    assert(actual == expected)
  }

  test("should parse nested-expression to the correct AST") {
    val actual: QLForm = generateQLForm("/positive/nested-expression.ql")
    val expected = QLForm(
      formName = "Box1HouseOwning",
      statements = List(
        Question(id = "hasSoldHouse", label = "Did you sell a house in 2010?", answer = BooleanAnswer(None)),
        Conditional(
          condition = BinaryOperation(
            binaryOperator = Div,
            left = BinaryOperation(
              binaryOperator = Add,
              left = BinaryOperation(
                binaryOperator = Mul,
                left = BinaryOperation(
                  binaryOperator = Sub,
                  left = IntAnswer(Some(10000)),
                  right = Reference("hasSoldHouse")
                ),
                right = IntAnswer(Some(42))
              ),
              right = IntAnswer(Some(23))
            ),
            right = IntAnswer(Some(54))),
          ifStatements = List(
            Question(id = "asd", label = "asd", answer = BooleanAnswer(None))
          ),
          elseStatements = List())
      )
    )

    assert(actual == expected)
  }

  test("should parse simple-expression to the correct AST") {
    val actual: QLForm = generateQLForm("/positive/simple-expression.ql")

    val expected: QLForm =
      QLForm(
        formName = "Box1HouseOwning",
        statements = List(
          Question(id = "hasSoldHouse", label = "Did you sell a house in 2010?", answer = BooleanAnswer(None)),
          Question(id = "houseSellingPrice", label = "What was the selling price?", answer = BinaryOperation(Sub, IntAnswer(Some(10000)), Reference("hasSoldHouse")))
        )
      )

    assert(actual == expected)
  }
}
