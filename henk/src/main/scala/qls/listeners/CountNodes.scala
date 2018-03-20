package qls.listeners

import grammar._
import org.antlr.v4.runtime._
import org.antlr.v4.runtime.tree._;
import scala.util.Try

class CountNodesListener extends QlsParserBaseListener {
  val node_count = collection.mutable.Map[String, Int]().withDefaultValue(0)

  override def enterRoot(ctx: QlsParser.RootContext): Unit = {
    node_count("root") += 1;
  }

  override def enterPage(ctx: QlsParser.PageContext): Unit = {
    node_count("page") += 1;
  }

  override def enterSection(ctx: QlsParser.SectionContext): Unit = {
    node_count("section") += 1;
  }

  override def enterIdentifier(ctx: QlsParser.IdentifierContext): Unit = {
    node_count("identifier") += 1;
  }

  override def enterQuestion(ctx: QlsParser.QuestionContext): Unit = {
    node_count("question") += 1;
  }
}
