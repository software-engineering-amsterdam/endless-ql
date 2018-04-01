package ql.common

import org.antlr.v4.runtime.ParserRuleContext
import org.antlr.v4.runtime.Token
import org.antlr.v4.runtime.tree.TerminalNode
import ql.ast.location.SourceLocation

fun TerminalNode.location() = SourceLocation(
        symbol.line, symbol.charPositionInLine, symbol.startIndex, symbol.stopIndex
)

fun Token.location() = SourceLocation(
        line, charPositionInLine, startIndex, stopIndex
)

fun ParserRuleContext.location() = SourceLocation(
        start.line, start.charPositionInLine, start.startIndex, stop.stopIndex
)