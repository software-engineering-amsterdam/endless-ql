import grammar._

import org.antlr.v4.runtime._

object Main extends App {

  def getParser(input:String): QLParser = {
    val charStream = new ANTLRInputStream(input)
    val lexer = new QLLexer(charStream)
    val tokens = new CommonTokenStream(lexer)
    val parser = new QLParser(tokens)
    return parser
  }

  def getQlsParser(input:String): QlsParser = {
    val charStream = new ANTLRInputStream(input)
    val lexer = new QlsLexer(charStream)
    val tokens = new CommonTokenStream(lexer)
    val parser = new QlsParser(tokens)
    return parser
  }
}
