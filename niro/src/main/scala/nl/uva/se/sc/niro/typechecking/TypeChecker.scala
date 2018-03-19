package nl.uva.se.sc.niro.typechecking

import cats.implicits._
import nl.uva.se.sc.niro.ExpressionEvaluator._
import nl.uva.se.sc.niro.errors.Errors.TypeCheckError
import nl.uva.se.sc.niro.errors.Warning
import nl.uva.se.sc.niro.model.ql.SymbolTable.SymbolTable
import nl.uva.se.sc.niro.model.ql.expressions._
import nl.uva.se.sc.niro.model.ql.expressions.answers._
import nl.uva.se.sc.niro.model.ql.{ DecimalType, MoneyType, _ }
import nl.uva.se.sc.niro.typechecking.CycleDetection._
import org.apache.logging.log4j.scala.Logging

object TypeChecker extends Logging {

  /** Performs all type checking phases. Early aborts when one of the tasks returns a TypeCheckError
    * */
  // Order of execution is important here to avoid infinite loops in subsequent phases
  def pipeline(qLForm: QLForm): Either[Seq[TypeCheckError], QLForm] =
    for {
      _ <- checkReferences(qLForm).left.map(error => Seq(error))
      _ <- checkCyclicDependenciesBetweenQuestions(qLForm).left.map(error => Seq(error))
      _ <- checkOperandsOfInvalidTypeToOperators(qLForm)
      _ <- checkNonBooleanPredicates(qLForm).left.map(error => Seq(error))
      _ <- checkDuplicateQuestionDeclarationsWithDifferentTypes(qLForm).left.map(error => Seq(error))
      qlFormWithPossibleWarnings = checkDuplicateLabels(qLForm)
    } yield qlFormWithPossibleWarnings

  private def checkReferences(qLForm: QLForm): Either[TypeCheckError, QLForm] = {
    logger.info("Phase 1 - Checking references to undefined questions ...")

    val references: Seq[Reference] = Statement
      .collectAllExpressions(qLForm.statements)
      .flatMap(Expression.collectAllReferences)

    val undefinedReferences: Seq[String] = references.map(_.questionId).filterNot(qLForm.symbolTable.contains)

    if (undefinedReferences.nonEmpty) {
      Left(TypeCheckError(message = s"Undefined references: $undefinedReferences"))
    } else {
      Right(qLForm)
    }
  }

  private def checkCyclicDependenciesBetweenQuestions(qLForm: QLForm): Either[TypeCheckError, QLForm] = {
    logger.info("Phase 2 - Checking cyclic dependencies between questions ...")

    val questions: Seq[Question] = Statement.collectAllQuestions(qLForm.statements)

    val dependencyGraph: Graph = buildDependencyGraph(questions)

    val cyclicDependencies: Seq[Graph] =
      dependencyGraph.flatMap(element => detectCycles(dependencyGraph, Seq(element)))

    if (cyclicDependencies.nonEmpty) {
      Left(TypeCheckError(message = s"Found cyclic dependencies: ${cyclicDependencies.map(graphToString)}"))
    } else {
      Right(qLForm)
    }
  }

  private def checkNonBooleanPredicates(qLForm: QLForm): Either[TypeCheckError, QLForm] = {
    logger.info("Phase 3 - Checking predicates that are not of the type boolean ...")

    val conditionals: Seq[Conditional] = Statement.collectAllConditionals(qLForm.statements)
    val conditionalsWithNonBooleanPredicates: Seq[Conditional] = conditionals filter { conditional =>
      typeOf(conditional.predicate, qLForm.symbolTable) match {
        case Right(BooleanType) => false
        case _                      => true
      }
    }

    if (conditionalsWithNonBooleanPredicates.nonEmpty) {
      Left(TypeCheckError(message = s"Non boolean predicate: $conditionalsWithNonBooleanPredicates"))
    } else {
      Right(qLForm)
    }
  }

  private def checkDuplicateQuestionDeclarationsWithDifferentTypes(qLForm: QLForm): Either[TypeCheckError, QLForm] = {
    logger.info("Phase 4 - Checking duplicate question declarations with different types ...")

    val questions: Seq[Question] = Statement.collectAllQuestions(qLForm.statements)
    val duplicateQuestions: Iterator[Seq[Question]] = questions.groupBy(_.id).valuesIterator.filter(_.size > 1)

    val duplicateQuestionsWithDifferentTypes: Seq[Seq[Question]] = duplicateQuestions
      .filter(questionsWithMultipleOccurrences => questionsWithMultipleOccurrences.map(_.answerType).toSet.size > 1)
      .toList

    if (duplicateQuestionsWithDifferentTypes.nonEmpty) {
      Left(
        TypeCheckError(
          message = s"Duplicate question declarations with different types: $duplicateQuestionsWithDifferentTypes"))
    } else {
      Right(qLForm)
    }
  }

  private def buildDependencyGraph(questions: Seq[Question]): Graph = {
    questions.collect {
      case q @ Question(_, _, _, Some(r @ Reference(_))) => Seq(Edge(q.id, r.questionId))
      case q @ Question(_, _, _, Some(expression)) =>
        Expression.collectAllReferences(expression).map(r => Edge(q.id, r.questionId))
    }.flatten
  }

  // TODO implement checkOperandsOfInvalidTypeToOperators
  private def checkOperandsOfInvalidTypeToOperators(qLForm: QLForm): Either[List[TypeCheckError], QLForm] = {
    logger.info("Phase 3 - Checking operands of invalid type to operators ...")

    val conditionals = Statement.collectAllConditionals(qLForm.statements)
    val expressions: Iterable[Expression] = qLForm.symbolTable.values.map(_.expression).flatten
    val predicates = conditionals.map(_.predicate)

    (expressions ++ predicates)
      .map(expression => typeOf(expression, qLForm.symbolTable))
      .map(either => either.toValidatedNel)
      .toList
      .sequenceU_
      .as(AnswerType)
      .toEither
      .left
      .map(_.toList)
      .right
      .map(_ => qLForm)
  }

  def typeOf(expr: Expression, symbolTable: SymbolTable): Either[TypeCheckError, AnswerType] = expr match {
    case Reference(questionId) => symbolTable(questionId).answerType.asRight

    case UnaryOperation(operator: Operator, expression) =>
      typeOf(expression, symbolTable).flatMap(answerType => checkOperandAndOperator(operator, answerType))

    case BinaryOperation(operator: Operator, leftExpression, rightExpression) =>
      for {
        leftType <- typeOf(leftExpression, symbolTable)
        leftTypeAfterOperation <- checkOperandAndOperator(operator, leftType)
        rightType <- typeOf(rightExpression, symbolTable)
        rightTypeAfterOperation <- checkOperandAndOperator(operator, rightType)
        result <- checkLeftRight(leftTypeAfterOperation, rightTypeAfterOperation)
      } yield result

    case _: IntegerAnswer => IntegerType.asRight
    case _: DecimalAnswer => DecimalType.asRight
    case _: MoneyAnswer   => MoneyType.asRight
    case _: BooleanAnswer => BooleanType.asRight
    case _: StringAnswer  => StringType.asRight
    case _: DateAnswer    => DateType.asRight
  }

  // TODO implement type widener
  def checkLeftRight(leftType: AnswerType, rightType: AnswerType): Either[TypeCheckError, AnswerType] = {
    if (leftType != rightType)
      TypeCheckError(message = s"Operands of invalid type: $leftType, $rightType").asLeft
    else
      rightType.asRight
  }

  // TODO make typecheckable type class
  private def checkOperandAndOperator(operator: Operator, operand: AnswerType): Either[TypeCheckError, AnswerType] = {
    operator match {
      case _: ArithmeticOperator =>
        operand match {
          case IntegerType => IntegerType.asRight
          case DecimalType => DecimalType.asRight
          case MoneyType   => MoneyType.asRight
          case _           => TypeCheckError(message = s"Operand: $operand of invalid type to operator: $operator").asLeft
        }
      case _: BooleanOperator =>
        operand match {
          case IntegerType => BooleanType.asRight
          case DecimalType => BooleanType.asRight
          case MoneyType   => BooleanType.asRight
          case BooleanType => BooleanType.asRight
          case DateType    => BooleanType.asRight
          case StringType  => BooleanType.asRight
          case _           => TypeCheckError(message = s"Operand: $operand of invalid type to operator: $operator").asLeft
        }
      case _: LogicalOperator =>
        operand match {
          case BooleanType => BooleanType.asRight
          case _           => TypeCheckError(message = s"Operand: $operand of invalid type to operator: $operator").asLeft
        }
      case _ => TypeCheckError(message = s"Operator: $operator not implemented").asLeft
    }
  }

  private def checkDuplicateLabels(qLForm: QLForm): QLForm = {
    logger.info("Phase 6 - Checking duplicate question labels ...")

    val questions: Seq[Question] = Statement.collectAllQuestions(qLForm.statements)
    val questionsWithDuplicateLabels: Seq[Seq[Question]] =
      questions.groupBy(_.label).valuesIterator.filter(_.size > 1).toList

    val warnings = questionsWithDuplicateLabels
      .map(
        duplicates =>
          Warning(
            s"Warning: questions ${duplicates.map(_.id).mkString(", ")} have duplicate label: ${duplicates.head.label}"
        ))

    qLForm.copy(warnings = warnings)
  }
}
