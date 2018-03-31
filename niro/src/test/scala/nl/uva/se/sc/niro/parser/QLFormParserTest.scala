package nl.uva.se.sc.niro.parser

import java.io.IOException

import nl.uva.se.sc.niro.model.ql._
import nl.uva.se.sc.niro.model.ql.expressions._
import nl.uva.se.sc.niro.model.ql.expressions.answers._
import org.antlr.v4.runtime.{ CharStream, CharStreams }
import org.scalatest.FunSuite

class QLFormParserTest extends FunSuite {
  @throws[IOException]
  private def toCharStream(filePath: String): CharStream =
    CharStreams.fromStream(getClass.getResourceAsStream(filePath))

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
            BooleanType,
            expression = None
          ),
          Question(
            id = "hasBoughtHouse",
            label = "Did you by a house in 2010?",
            BooleanType,
            expression = None
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
            StringType,
            expression = None
          ),
          Conditional(
            predicate = BooleanAnswer(true),
            thenStatements = List(
              Question(id = "middleName", label = "What is your middle name?", StringType, expression = None)
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
        Question("firstName", "What is your first name?", StringType, None),
        Conditional(
          predicate = Negate(BooleanAnswer(true)),
          thenStatements = List(Question("middleName", "What is your middle name?", StringType, None))
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
        Question(id = "firstName", "What is your first name?", StringType, expression = None),
        Conditional(
          predicate = BooleanAnswer(true),
          thenStatements = List(
            Question(id = "middleName", label = "What is your middle name?", StringType, expression = None)
          )
        ),
        Conditional(
          predicate = Negate(BooleanAnswer(true)),
          thenStatements = List(
            Question("lastName", "What is your last name?", StringType, None),
            Question("lastName", "What is your last name?", StringType, None)
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
        Question(id = "firstName", label = "What is your first name?", StringType, expression = None),
        Conditional(
          predicate = BooleanAnswer(true),
          thenStatements = List(
            Question("lastName", "What is your last name?", StringType, expression = None)
          )
        ),
        Conditional(
          predicate = Negate(BooleanAnswer(true)),
          thenStatements = List(
            Question("middleName", "What is your middle name?", StringType, None),
            Conditional(BooleanAnswer(false), List(Question("lastName", "What is your last name?", StringType, None))),
            Conditional(
              Negate(BooleanAnswer(false)),
              List(Question("middleName", "What is your middle name?", StringType, None)))
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
        Question("firstName", "What is your first name?", StringType, None),
        Question("lastName", "What is your last name?", StringType, None),
        Question("middleName", "Do you have a middle name?", BooleanType, None),
        Conditional(
          predicate = And(
            NotEqual(Reference("lastName"), Reference("firstName")),
            GreaterThen(Reference("a"), Minus(IntegerAnswer(10)))),
          thenStatements = List(
            Question(id = "lastName", label = "What is your last name?", StringType, expression = None)
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
        Question("firstName", "What is your first name?", StringType, None),
        Question("lastName", "What is your last name?", StringType, None),
        Question("middleName", "Do you have a middle name?", BooleanType, None),
        Conditional(
          predicate = And(
            NotEqual(Reference("lastName"), Reference("firstName")),
            GreaterThen(Reference("a"), Minus(IntegerAnswer(10)))),
          thenStatements = List(
            Question("lastName", "What is your last name?", StringType, None),
            Question("lastName", "What is your last name?", StringType, None)
          )
        ),
        Conditional(
          predicate = Negate(
            And(
              NotEqual(Reference("lastName"), Reference("firstName")),
              GreaterThen(Reference("a"), Minus(IntegerAnswer(10))))
          ),
          thenStatements = List(
            Question("middleName", "Do you have a middle name?", BooleanType, None),
            Question("middleName", "Do you have a middle name?", BooleanType, None)
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
        Question(id = "hasSoldHouse", label = "Did you sell a house in 2010?", BooleanType, expression = None),
        Conditional(
          predicate = Addition(
            left = Multiply(
              left = Subtract(left = IntegerAnswer(10000), right = Reference("hasSoldHouse")),
              right = IntegerAnswer(42)
            ),
            right = Divide(left = IntegerAnswer(23), right = IntegerAnswer(54))
          ),
          thenStatements = List(
            Question(id = "asd", label = "asd", BooleanType, expression = None)
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
          Question(id = "hasSoldHouse", label = "Did you sell a house in 2010?", IntegerType, expression = None),
          Question(
            id = "houseBuyingPrice",
            label = "What was the buying price?",
            IntegerType,
            expression = Some(IntegerAnswer(10000))
          ),
          Question(
            id = "houseSellingPrice",
            label = "What was the selling price?",
            IntegerType,
            expression = Some(Subtract(IntegerAnswer(10000), Reference("hasSoldHouse")))
          )
        )
      )

    assert(actual == expected)
  }

  test("should parse dangling else to the correct AST") {
    val actual: QLForm = generateQLForm("/positive/dangling-else.ql")

    val expected: QLForm =
      QLForm(
        "DanglingElse",
        List(
          Conditional(
            BooleanAnswer(true),
            List(Question("firstIf", "FirstIf", DecimalType, None))
          ),
          Conditional(
            BooleanAnswer(false),
            List(Question("firstElseIf", "FirstElseIf", IntegerType, None))
          ),
          Conditional(
            Negate(BooleanAnswer(false)),
            List(Question("danglingElse", "DanglingElse", BooleanType, None))
          )
        )
      )

    assert(actual == expected)
  }

  test("should parse money to the correct AST") {
    val actual: QLForm = generateQLForm("/positive/money.ql")

    val expected: QLForm = QLForm(
      "Box1HouseOwning",
      List(
        Question("money1", "Money1:", MoneyType, None),
        Question("money2", "Money2:", MoneyType, Some(MoneyAnswer(1))),
        Question(
          "money3",
          "Money3",
          MoneyType,
          Some(Addition(Reference("money1"), Reference("money2")))
        ),
        Question("money2", "Money4:", MoneyType, Some(MoneyAnswer(1.0)))
      ),
      List()
    )

    assert(actual == expected)
  }
}
