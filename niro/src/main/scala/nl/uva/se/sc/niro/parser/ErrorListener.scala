package nl.uva.se.sc.niro.parser

import java.util

import nl.uva.se.sc.niro.errors.Errors._
import org.antlr.v4.runtime.atn.ATNConfigSet
import org.antlr.v4.runtime.dfa.DFA
import org.antlr.v4.runtime.misc.Interval
import org.antlr.v4.runtime.{ ANTLRErrorListener, Parser, RecognitionException, Recognizer }
import org.apache.logging.log4j.scala.Logging

import scala.collection.mutable.ListBuffer

class ErrorListener extends Logging with ANTLRErrorListener {
  var parseErrors = new ListBuffer[Error]

  override def syntaxError(
      recognizer: Recognizer[_, _],
      offendingSymbol: Any,
      line: Int,
      charPositionInLine: Int,
      msg: String,
      e: RecognitionException): Unit = {
    parseErrors += SyntaxErrorInfo(line, charPositionInLine, offendingSymbol.toString, msg, e)
  }

  override def reportAmbiguity(
      parser: Parser,
      dfa: DFA,
      startIndex: Int,
      stopIndex: Int,
      exact: Boolean,
      ambigAlts: util.BitSet,
      configs: ATNConfigSet): Unit = {
    val interval = Interval.of(startIndex, stopIndex)
    logger.info(s"Ambiguous construct around [${parser.getTokenStream.getText(interval)}].")
  }

  override def reportAttemptingFullContext(
      parser: Parser,
      dfa: DFA,
      startIndex: Int,
      stopIndex: Int,
      conflictingAlts: util.BitSet,
      configs: ATNConfigSet): Unit = {
    val interval = Interval.of(startIndex, stopIndex)
    logger.info(s"Need full context on [${parser.getTokenStream.getText(interval)}] to make decision.")
  }

  override def reportContextSensitivity(
      parser: Parser,
      dfa: DFA,
      startIndex: Int,
      stopIndex: Int,
      prediction: Int,
      configs: ATNConfigSet): Unit = {
    val interval = Interval.of(startIndex, stopIndex)
    logger.info(s"Context sensitive construct while dealing with [${parser.getTokenStream.getText(interval)}].")
  }
}
