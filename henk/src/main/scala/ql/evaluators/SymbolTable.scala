package ql.evaluators

import ql.collectors._
import ql.models.ast._

class SymbolTableEvaluator(ast: Root) {
  var state = collection.mutable.Map[Identifier, ExpressionValue]()

  def getReachableQuestions(st: collection.mutable.Map[Identifier, ExpressionValue]): List[Question] = {
    val stateHolder = this.state
    this.state = st
    val result = FormCollector.getStatements(ast).map(reachableStatements).flatten.collect {
      case q: Question => q
    }
    this.state = stateHolder
    return result 
  }

  def getQuestions(): List[Question] = {
    FormCollector.getStatements(ast).map(reachableStatements).flatten.collect {
      case q: Question => q
    }
  }

  def reachableStatements(statement: Statement): List[Statement] = {
    statement match {
      case q: Question => List(q)
      case comp: Computation => List(comp)
      case ifStmt: IfStatement => ifStmt.statements.flatMap(reachableStatements)
      case elseStmt: ElseStatement => elseStmt.statements.flatMap(reachableStatements)
      case condition: ConditionalStatement => {
        val predicate = condition.ifStmt.expression
        if(evaluateExpression(predicate) == BooleanValue(true)) {
          reachableStatements(condition.ifStmt)
        } else {
          condition.elseStmt.map(reachableStatements).getOrElse(List())
        }
      }
    }
  }

  def evaluateEquality(expression: EqualityOperand): BooleanValue = {
    val lhs = evaluateExpression(expression.lhs)
    val rhs = evaluateExpression(expression.rhs)

    expression match {
      case EqualOp(_,_) if lhs == rhs => BooleanValue(true)
      case NotEqualOp(_,_) if lhs != rhs => BooleanValue(true)
      case other => BooleanValue(false)
    }
  }

  def evaluateBooleanExpression(expression: Expression): BooleanValue = {
    expression match {
      case bv: BooleanValue => bv
      case bv: EqualityOperand => evaluateEquality(bv)
      case bv: RelationalOperand => evaluateRelational(bv)
      case bv: LogicalOperand => evaluateLogical(bv)
      case id: Identifier => this.state.get(id).getOrElse(BooleanValue(false)) match {
        case iv : BooleanValue => iv
      }
    }
  }

  def evaluateIntegerExpression(expression: Expression): IntegerValue = {
    expression match {
      case bv: IntegerValue => bv
      case ao: ArithmeticOperand => evaluateArithmetic(ao)
      case id: Identifier => this.state.get(id).getOrElse(IntegerValue(1)) match {
        case iv : IntegerValue => iv
      }
    }
  }

  def evaluateLogical(expression: LogicalOperand): BooleanValue = {
    val lhs = evaluateBooleanExpression(expression.lhs)
    val rhs = evaluateBooleanExpression(expression.rhs)

    expression match {
      case LogicalConOp(_,_) if lhs && rhs => BooleanValue(true)
      case LogicalDisOp(_,_) if lhs || rhs => BooleanValue(true)
      case other => BooleanValue(false)
    }
  }

  def evaluateRelational(expression: RelationalOperand): BooleanValue = {
    val lhs = evaluateIntegerExpression(expression.lhs)
    val rhs = evaluateIntegerExpression(expression.rhs)

    expression match {
      case RelationalLTOp(_, _) if lhs < rhs => BooleanValue(true)
      case RelationalLTEOp(_, _) if lhs <= rhs => BooleanValue(true)
      case RelationalGTOp(_, _) if lhs > rhs => BooleanValue(true)
      case RelationalGTEOp(_, _) if lhs >= rhs => BooleanValue(true)
      case other => BooleanValue(false)
    }
  }

  def evaluateArithmetic(expression: ArithmeticOperand): IntegerValue = {
    val lhs = evaluateIntegerExpression(expression.lhs)
    val rhs = evaluateIntegerExpression(expression.rhs)

    expression match {
      case AddOp(_, _) => lhs + rhs
      case MinOp(_, _) => lhs - rhs
      case DivOp(_, _) => lhs / rhs
      case MulOp(_, _) => lhs * rhs
    }
  }

  def evaluateBinOp(expression: BinaryOperand): ExpressionValue = {
    expression match {
      case eo: EqualityOperand => evaluateEquality(eo)
      case lo: LogicalOperand => evaluateLogical(lo)
      case lo: RelationalOperand => evaluateRelational(lo)
      case ao: ArithmeticOperand => evaluateArithmetic(ao)
    }
  }

  def evaluateUnary(expression: UnaryOperand): ExpressionValue = {
    val some = evaluateExpression(expression.expr)
    expression match {
      case UnaryNotOp(_) => !evaluateBooleanExpression(some)
      case UnaryMinOp(_) => -evaluateIntegerExpression(some)
    }
  }

  def evaluateIdentifier(identifier: Identifier): ExpressionValue = {
    this.state.get(identifier).getOrElse(getDefaultValue(identifier))
  }

  def getDefaultValue(id: Identifier): ExpressionValue = {
    val nodeType = TypeCollector.getTypeDecl(id, this.ast).get
    nodeType match {
      case BooleanType => BooleanValue(false)
      case IntegerType => IntegerValue(0)
      case StringType => StringValue("")
    }
  }

  def evaluateExpression(expression: Expression): ExpressionValue = {
    expression match {
      case ev : ExpressionValue => ev
      case bo : BinaryOperand => evaluateBinOp(bo)
      case uo : UnaryOperand => evaluateUnary(uo)
      case id : Identifier => this.state.get(id).getOrElse(getDefaultValue(id))
    }
  }
}
