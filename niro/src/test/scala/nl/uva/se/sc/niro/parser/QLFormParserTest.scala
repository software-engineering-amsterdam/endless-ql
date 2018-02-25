package nl.uva.se.sc.niro.parser

import java.io.IOException

import nl.uva.se.sc.niro.model.Expressions.answers.{ BooleanAnswer, IntAnswer, StringAnswer }
import nl.uva.se.sc.niro.model.Expressions.{ BinaryOperation, Reference, UnaryOperation }
import nl.uva.se.sc.niro.model._
import org.antlr.v4.runtime.{ CharStream, CharStreams }
import org.scalatest.FunSuite

class QLFormParserTest extends FunSuite {
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
            expression = BooleanAnswer(None)
          ),
          Question(
            id = "hasBoughtHouse",
            label = "Did you by a house in 2010?",
            expression = BooleanAnswer(None)
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
            expression = StringAnswer(None)
          ),
          Conditional(
            predicate = BooleanAnswer(Some(true)),
            thenStatements = List(
              Question(id = "middleName", label = "What is your middle name?", expression = StringAnswer(None))
            )
          )
        )
      )


    assert(actual == expected)
  }

  test("should parse not-conditional-if to the correct AST") {
    val actual: QLForm = generateQLForm("/positive/not-conditional-if.ql")
    val expected = QLForm(
      "ConditionQuestions",
      List(
        Question("firstName","What is your first name?",StringAnswer(None)),
        Conditional(
          predicate = UnaryOperation(Neg, BooleanAnswer(Some(true))),
          thenStatements = List(Question("middleName", "What is your middle name?", StringAnswer(None))
          )
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
        Question(id = "firstName", "What is your first name?", expression = StringAnswer(None)),
        Conditional(
          predicate = BooleanAnswer(Some(true)),
          thenStatements = List(
            Question(id = "middleName", label = "What is your middle name?", expression = StringAnswer(None))
          )
        ),
        Conditional(
          predicate = UnaryOperation(Neg, BooleanAnswer(Some(true))),
          thenStatements = List(
            Question("lastName", "What is your last name?", StringAnswer(None)),
            Question("lastName", "What is your last name?", StringAnswer(None))
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
        Question(id = "firstName", label = "What is your first name?", expression = StringAnswer(None)),
        Conditional(
          predicate = BooleanAnswer(Some(true)),
          thenStatements = List(
            Question("lastName", "What is your last name?",expression = StringAnswer(None))
          )
        ),
        Conditional(
          predicate = UnaryOperation(Neg, BooleanAnswer(Some(true))),
          thenStatements = List(
            Question("middleName", "What is your middle name?", StringAnswer(None)),
            Conditional(BooleanAnswer(Some(false)), List(Question("lastName", "What is your last name?", StringAnswer(None)))),
            Conditional(UnaryOperation(Neg, BooleanAnswer(Some(false))), List(Question("middleName", "What is your middle name?", StringAnswer(None))))
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
        Question("firstName", "What is your first name?", StringAnswer(None)),
        Question("lastName", "What is your last name?", StringAnswer(None)),
        Question("middleName", "Do you have a middle name?", BooleanAnswer(None)),
        Conditional(
          predicate = BinaryOperation(And, BinaryOperation(Ne, Reference("lastName"), Reference("firstName")), BinaryOperation(Gt, Reference("a"), UnaryOperation(Min, IntAnswer(Some(10))))),
          thenStatements = List(
            Question(id = "lastName", label = "What is your last name?", expression = StringAnswer(None))
          )
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
        Question("firstName", "What is your first name?", StringAnswer(None)),
        Question("lastName", "What is your last name?", StringAnswer(None)),
        Question("middleName", "Do you have a middle name?", BooleanAnswer(None)),
        Conditional(
          predicate = BinaryOperation(And, BinaryOperation(Ne, Reference("lastName"), Reference("firstName")), BinaryOperation(Gt, Reference("a"), UnaryOperation(Min, IntAnswer(Some(10))))),
          thenStatements = List(
            Question("lastName", "What is your last name?", StringAnswer(None)),
            Question("lastName", "What is your last name?", StringAnswer(None))
          )
        ),
        Conditional(
          predicate = UnaryOperation(Neg, BinaryOperation(And, BinaryOperation(Ne, Reference("lastName"), Reference("firstName")), BinaryOperation(Gt, Reference("a"), UnaryOperation(Min, IntAnswer(Some(10)))))),
          thenStatements = List(
            Question("middleName", "Do you have a middle name?", BooleanAnswer(None)),
            Question("middleName", "Do you have a middle name?", BooleanAnswer(None))
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
        Question(id = "hasSoldHouse", label = "Did you sell a house in 2010?", expression = BooleanAnswer(None)),
        Conditional(
          predicate = BinaryOperation(
            binaryOperator = Add,
            left = BinaryOperation(
              binaryOperator = Mul,
              left = BinaryOperation(
                binaryOperator = Sub,
                left = IntAnswer(Some(10000)),
                right = Reference("hasSoldHouse")),
              right = IntAnswer(Some(42))),
            right = BinaryOperation(
              binaryOperator = Div,
              left = IntAnswer(Some(23)),
              right = IntAnswer(Some(54)))),
          thenStatements = List(
            Question(id = "asd", label = "asd", expression = BooleanAnswer(None))
          )
        )
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
          Question(id = "hasSoldHouse", label = "Did you sell a house in 2010?", expression = BooleanAnswer(None)),
          Question(id = "houseSellingPrice", label = "What was the selling price?", expression = BinaryOperation(Sub, IntAnswer(Some(10000)), Reference("hasSoldHouse")))
        )
      )

    assert(actual == expected)
  }
}
