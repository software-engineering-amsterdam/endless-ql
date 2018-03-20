import ql.grammar._

import org.antlr.v4.runtime._

object Main extends App {

  def getParser(input:String): ql.grammar.QlParser = {
    val charStream = new ANTLRInputStream(input)
    val lexer = new QlLexer(charStream)
    val tokens = new CommonTokenStream(lexer)
    val parser = new QlParser(tokens)
    return parser
  }

  def getQlsParser(input:String): ql.grammar.QlsParser = {
    val charStream = new ANTLRInputStream(input)
    val lexer = new QlsLexer(charStream)
    val tokens = new CommonTokenStream(lexer)
    val parser = new QlsParser(tokens)
    return parser
  }
}
