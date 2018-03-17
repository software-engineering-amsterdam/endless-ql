package nl.uva.se.sc.niro.parser

import nl.uva.se.sc.niro.errors.Errors.Error
import nl.uva.se.sc.niro.model.qls.{ Page, QLStylesheet, Question, Section }
import org.antlr.v4.runtime.{ CharStream, CommonTokenStream }
import org.apache.logging.log4j.scala.Logging
import qls.{ QLSBaseVisitor, QLSLexer, QLSParser }

import scala.collection.JavaConverters
import scala.collection.mutable.ListBuffer

object QLStylesheetParser extends Logging {
  private val errorListener = new ErrorListener

  def getParseErrors: ListBuffer[Error] = errorListener.parseErrors

  def parse(formSource: CharStream): QLStylesheet = {
    logger.traceEntry()
    errorListener.parseErrors.clear()
    val parser = new QLSParser(new CommonTokenStream(new QLSLexer(formSource)))
    parser.removeErrorListeners()
    parser.addErrorListener(errorListener)
    logger.traceExit(StylesheetVisitor.visit(parser.stylesheet()))
  }

  object StylesheetVisitor extends QLSBaseVisitor[QLStylesheet] {
    override def visitStylesheet(ctx: QLSParser.StylesheetContext): QLStylesheet = {
      val pages = JavaConverters.asScalaBuffer(ctx.page).map(PageVisitor.visit)
      QLStylesheet(ctx.name.getText, pages)
    }
  }

  object PageVisitor extends QLSBaseVisitor[Page] {
    override def visitPage(ctx: QLSParser.PageContext): Page = {
      val sections = JavaConverters.asScalaBuffer(ctx.section()).map(SectionVisitor.visit)
      Page(ctx.name.getText, sections)
    }
  }

  object SectionVisitor extends QLSBaseVisitor[Section] {
    override def visitSection(ctx: QLSParser.SectionContext): Section = {
      val questions = JavaConverters.asScalaBuffer(ctx.question()).map(QuestionVisitor.visit)
      Section(ctx.name.getText, questions)
    }
  }

  object QuestionVisitor extends QLSBaseVisitor[Question] {
    override def visitQuestion(ctx: QLSParser.QuestionContext): Question = {
      Question(ctx.name.getText)
    }
  }
}
