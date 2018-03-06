package nl.uva.se.sc.niro.gui.util

import nl.uva.se.sc.niro.parser.ParseErrorInfo

import scala.collection.mutable.ListBuffer

object ErrorUtil {
  def toString(parseErrors: ListBuffer[ParseErrorInfo]): String = {
    val message = new StringBuilder
    for (errorInfo <- parseErrors) {
      message.append(errorInfo.toString)
      message.append("\n")
    }
    message.toString
  }
}