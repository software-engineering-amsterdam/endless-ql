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

  def getReachableComputations(st: collection.mutable.Map[Identifier, ExpressionValue]): List[Computation] = {
    val stateHolder = this.state
    this.state = st
    val result = FormCollector.getStatements(ast).map(reachableStatements).flatten.collect {
      case comp: Computation => comp
    }
    this.state = stateHolder
    return result 
  }

  def getReachableStatements(st: collection.mutable.Map[Identifier, ExpressionValue]): List[Statement] = {
    val stateHolder = this.state
    this.state = st
    evaluateComputations(st)

    val result = FormCollector.getStatements(ast).map(reachableStatements).flatten.collect {
      case comp: Computation => comp
      case quest: Question => quest
    }
    this.state = stateHolder
    return result 
  }

  def evaluableComputations(st: collection.mutable.Map[Identifier, ExpressionValue]): List[Computation] = {
    val computations = FormCollector.getStatements(ast)
      .flatMap(StatementCollector.getComputations)

      computations.filter(x => {
        val identifiers = ExpressionCollector.getIdentifiers(x.valAssign)
        val values = identifiers.map(this.state.get(_))
        if(values.exists(x => x.isEmpty)) {
          false
        } else {
          true
        }
      })
  }

  def nonEvaluableComputations(st: collection.mutable.Map[Identifier, ExpressionValue]): List[Computation] = {
    val computations = FormCollector.getStatements(ast)
      .flatMap(StatementCollector.getComputations)

      computations.filter(x => {
        val identifiers = ExpressionCollector.getIdentifiers(x.valAssign)
        val values = identifiers.map(this.state.get(_))
        if(values.exists(x => x.isEmpty)) {
          true
        } else {
          false
        }
      })
  }

  def evaluateComputations(st: collection.mutable.Map[Identifier, ExpressionValue]): Unit = {
    // Try to evaluate all computations
    val computations = evaluableComputations(st)
    computations.map(x => {
      st.update(x.varDecl.id, evaluateComputation(x, st))
    })

    // Reset unreachable questions to none, otherwise computations
    // can still keep their value
    FormCollector.getStatements(ast)
      .flatMap(StatementCollector.getQuestions).diff(getQuestions)
      .map(q => {
        st.remove(q.varDecl.id)
      })

    // reset all computations in ST to none if they can't be evaluated
    // this is due to dependencies like questions being changed.
    nonEvaluableComputations(st).map(x => {
      st.remove(x.varDecl.id)
    })

    // resetting computations could trigger a ripple, invalidating
    // other computations so therefor we have to keep looking for
    // other computations until no computation is left that is
    // invaluable
    if(evaluableComputations(st).size < computations.size) {
      evaluateComputations(st)
    }
  }

  def getQuestions(): List[Question] = {
    FormCollector.getStatements(ast).map(reachableStatements).flatten.collect {
      case q: Question => q
    }
  }

  def evaluateComputation(comp: Computation, st: collection.mutable.Map[Identifier, ExpressionValue]): ExpressionValue = {
    val stateHolder = this.state
    this.state = st
    val result = evaluateExpression(comp.valAssign.expression)
    this.state = stateHolder
    return result 
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
