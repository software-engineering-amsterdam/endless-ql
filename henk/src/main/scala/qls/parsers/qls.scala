package qls.parsers

import grammar._
import qls.models.ast._
import qls.visitors._

import scala.io.Source
import java.net.URL

import org.antlr.v4.runtime._

object QLSParser {
  def getParser(input: String): QLSParser = {
    val charStream = new ANTLRInputStream(input)
    val lexer = new QLSLexer(charStream)
    val tokens = new CommonTokenStream(lexer)
    val parser = new QLSParser(tokens)
    return parser
  }

  def getForm(location: URL): Statement = {
    val source = Source.fromURL(location)
    val sourcedLines = source.mkString
    source.close

    val visitor = new StatementVisitor()
    val parser = getParser(sourcedLines)
    val tree = parser.root()

    return visitor.visit(tree)
  }

  def getRoot(location: URL): Root = {
    val source = Source.fromURL(location)
    val sourcedLines = source.mkString
    source.close

    val visitor = new StatementVisitor()
    val parser = getParser(sourcedLines)
    val tree = parser.root()

    return visitor.visitRoot(tree)
  }

}
