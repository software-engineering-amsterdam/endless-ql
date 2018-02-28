package ql.parsers

import ql.grammar._
import ql.models._
import ql.visitors._

import scala.io.Source

import java.net.URL

import org.antlr.v4.runtime._

object QlFormParser {
  def getParser(input:String): ql.grammar.QlParser = {
    val charStream = new ANTLRInputStream(input)
    val lexer = new QlLexer(charStream)
    val tokens = new CommonTokenStream(lexer)
    val parser = new QlParser(tokens)
    return parser
  }

  def parseFromURL(location:URL): ASTNode = {
    val source = Source.fromURL(location)
    val sourcedLines = source.mkString
    source.close

    val visitor = new SimplifierVisitor()
    val parser = getParser(sourcedLines)
    val tree = parser.root()

    return visitor.visit(tree)
  }
}
