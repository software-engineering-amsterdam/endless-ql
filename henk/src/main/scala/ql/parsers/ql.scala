package ql.parsers

import grammar._
import ql.models.ast._
import ql.visitors._

import scala.io.Source
import java.net.URL

import org.antlr.v4.runtime._

object QLParser {
  def getParser(input: String): QLParser = {
    val charStream = new ANTLRInputStream(input)
    val lexer = new QLLexer(charStream)
    val tokens = new CommonTokenStream(lexer)
    val parser = new QLParser(tokens)
    return parser
  }

  def getRoot(location: URL): Root = {
    val source = Source.fromURL(location)
    val sourcedLines = source.mkString
    source.close

    val visitor = new FormVisitor()
    val parser = getParser(sourcedLines)
    val tree = parser.root()

    return visitor.visitRoot(tree)
  }
}
