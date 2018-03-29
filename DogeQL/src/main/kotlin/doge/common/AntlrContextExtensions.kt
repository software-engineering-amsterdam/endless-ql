package doge.common

import doge.ast.location.SourceLocation
import org.antlr.v4.runtime.ParserRuleContext
import org.antlr.v4.runtime.Token
import org.antlr.v4.runtime.tree.TerminalNode

fun TerminalNode.location() = SourceLocation(
        symbol.line, symbol.charPositionInLine, symbol.startIndex, symbol.stopIndex
)

fun Token.location() = SourceLocation(
        line, charPositionInLine, startIndex, stopIndex
)

fun ParserRuleContext.location() = SourceLocation(
        start.line, start.charPositionInLine, start.startIndex, stop.stopIndex
)