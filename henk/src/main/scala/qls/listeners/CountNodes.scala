package qls.listeners

import grammar._
import org.antlr.v4.runtime._
import org.antlr.v4.runtime.tree._;
import scala.util.Try

class CountNodesListener extends QLSBaseListener {
  val node_count = collection.mutable.Map[String, Int]().withDefaultValue(0)

  override def enterRoot(ctx: QLSParser.RootContext): Unit = {
    node_count("root") += 1;
  }

  override def enterPage(ctx: QLSParser.PageContext): Unit = {
    node_count("page") += 1;
  }

  override def enterSection(ctx: QLSParser.SectionContext): Unit = {
    node_count("section") += 1;
  }

  override def enterIdentifier(ctx: QLSParser.IdentifierContext): Unit = {
    node_count("identifier") += 1;
  }

  override def enterQuestion(ctx: QLSParser.QuestionContext): Unit = {
    node_count("question") += 1;
  }
}
