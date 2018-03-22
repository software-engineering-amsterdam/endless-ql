package doge.ast.node

import doge.ast.node.expression.Expression
import doge.data.question.SymbolType

class QuestionStatement(
        val label: String,
        val name: String,
        val type: SymbolType,
        val expression: Expression?
) : Statement