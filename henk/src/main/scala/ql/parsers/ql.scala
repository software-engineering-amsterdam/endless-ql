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

    val visitor = new ASTVisitor()
    val parser = getParser(sourcedLines)
    val tree = parser.root()

    return visitor.visit(tree)
  }

  def retrieveTerminals(node: ASTNode): List[ASTNode] = {
    node match {
      case nt: ASTNonTerminal => traverseChildren(nt, retrieveTerminals)
      case other              => List(other)
    }
  }

  def retrieveIdentifiers(node: ASTNode): List[ASTNode] = {
    node match {
      case nt: ASTNonTerminal => traverseChildren(nt, retrieveIdentifiers)
      case id: ASTIdentifier  => List(id)
      case other              => List()
    }
  }

  def retrieveVarDecls(node: ASTNode): List[ASTNode] = {
    val flattened = flattenNT(node)
    flattened.collect { case varDecl: ASTVarDecl => varDecl }
  }

  // get ifConditionals from Root
  //   get non terminals -> collect conditionals
  // get identifiers without looking at formHeader
  //   get non terminals -> filter formHeader -> get terminals
  // get questions
  //   get non terminals -> collect questions
  // get identifier from vardec
  //   get non terminals -> collect vardec -> collect identifiers
  def traverseChildren(parent: ASTNode,
                       trav: (ASTNode) => List[ASTNode]): List[ASTNode] = {
    parent.flatten.map(trav).flatten
  }

  def flattenNT(node: ASTNode): List[ASTNode] = {
    node match {
      case nt: ASTNonTerminal => List(nt) ++ traverseChildren(nt, flattenNT)
      case other              => List()
    }
  }
}
