package nl.uva.se.sc.niro.util

object StringUtil {
  def addSpaceOnCaseChange(text: String): String = text.replaceAll("(\\p{Ll})(\\p{Lu})", "$1 $2")
}
