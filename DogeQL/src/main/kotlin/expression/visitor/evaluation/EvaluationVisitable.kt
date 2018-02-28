package expression.visitor.evaluation

import data.value.BaseSymbolValue
import expression.visitor.ExpressionVisitable

interface EvaluationVisitable: ExpressionVisitable<EvaluationVisitor, BaseSymbolValue>