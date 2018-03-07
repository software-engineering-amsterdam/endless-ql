package nl.uva.se.sc.niro.parser

import java.util
import java.util.Collections

import nl.uva.se.sc.niro.parser.errors.{ ParseErrorInfo, SyntaxErrorInfo }
import org.antlr.v4.runtime.atn.ATNConfigSet
import org.antlr.v4.runtime.dfa.DFA
import org.antlr.v4.runtime.misc.Interval
import org.antlr.v4.runtime.{ ANTLRErrorListener, Parser, RecognitionException, Recognizer }
import org.apache.logging.log4j.scala.Logging
import ql.QLParser

import scala.collection.JavaConverters
import scala.collection.mutable.ListBuffer

class ErrorListener extends Logging with ANTLRErrorListener {
  var parseErrors = new ListBuffer[ParseErrorInfo]

  override def syntaxError(
      recognizer: Recognizer[_, _],
      offendingSymbol: scala.Any,
      line: Int,
      charPositionInLine: Int,
      msg: String,
      e: RecognitionException): Unit = {
    val stack = recognizer.asInstanceOf[Parser].getRuleInvocationStack
    Collections.reverse(stack)
    parseErrors += new SyntaxErrorInfo(
      line,
      charPositionInLine,
      offendingSymbol.toString,
      msg,
      JavaConverters.asScalaBuffer(stack).toList,
      e)
  }

  override def reportAmbiguity(
      recognizer: Parser,
      dfa: DFA,
      startIndex: Int,
      stopIndex: Int,
      exact: Boolean,
      ambigAlts: util.BitSet,
      configs: ATNConfigSet): Unit = {
    val parser = recognizer.asInstanceOf[QLParser]
    val interval = Interval.of(startIndex, stopIndex)
    logger.info(s"Ambiguous construct around [${parser.getTokenStream.getText(interval)}].")
  }

  override def reportAttemptingFullContext(
      recognizer: Parser,
      dfa: DFA,
      startIndex: Int,
      stopIndex: Int,
      conflictingAlts: util.BitSet,
      configs: ATNConfigSet): Unit = {
    val parser = recognizer.asInstanceOf[QLParser]
    val interval = Interval.of(startIndex, stopIndex)
    logger.info(s"Need full context on [${parser.getTokenStream.getText(interval)}] to make decision.")
  }

  override def reportContextSensitivity(
      recognizer: Parser,
      dfa: DFA,
      startIndex: Int,
      stopIndex: Int,
      prediction: Int,
      configs: ATNConfigSet): Unit = {
    val parser = recognizer.asInstanceOf[QLParser]
    val interval = Interval.of(startIndex, stopIndex)
    logger.info(s"Context sensitive construct while dealing with [${parser.getTokenStream.getText(interval)}].")
  }
}
