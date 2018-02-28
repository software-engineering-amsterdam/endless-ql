package ql.parsers

import ql.grammar._
import ql.models.ast._
import ql.visitors._

import scala.io.Source

import java.net.URL

import org.antlr.v4.runtime._

object QlFormParser {
  def getParser(input: String): ql.grammar.QlParser = {
    val charStream = new ANTLRInputStream(input)
    val lexer = new QlLexer(charStream)
    val tokens = new CommonTokenStream(lexer)
    val parser = new QlParser(tokens)
    return parser
  }

  def parseFromURL(location: URL): ASTNode = {
    val source = Source.fromURL(location)
    val sourcedLines = source.mkString
    source.close

    val visitor = new SimplifierVisitor()
    val parser = getParser(sourcedLines)
    val tree = parser.root()

    return visitor.visit(tree)
  }

  def flattenNT(node: ASTNode): List[ASTNode] = {
    node match {
      case ASTRoot(header, body) => List(header) ++ flattenNT(body)
      case form @ ASTFormBody(statements) =>
        List(form) ++ statements.map(flattenNT).flatten
      case ifStmt @ ASTIfStatement(expr, statements) => {
        List(ifStmt) ++ statements.map(flattenNT).flatten ++ flattenNT(expr)
      }
      case binOp @ ASTBinary(lhs, rhs, op) => List(binOp, lhs, rhs, op)
      case other                           => List(other)
    }
  }
}
