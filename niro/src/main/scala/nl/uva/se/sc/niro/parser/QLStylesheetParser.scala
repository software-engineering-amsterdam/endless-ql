package nl.uva.se.sc.niro.parser

import java.util

import nl.uva.se.sc.niro.errors.Errors.Error
import nl.uva.se.sc.niro.model.ql.AnswerType
import nl.uva.se.sc.niro.model.qls._
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
      val defaultStyles = collectDefaultStyles(ctx.defaultStyle())
      QLStylesheet(ctx.name.getText, pages, defaultStyles)
    }
  }

  object PageVisitor extends QLSBaseVisitor[Page] {
    override def visitPage(ctx: QLSParser.PageContext): Page = {
      val sections = JavaConverters.asScalaBuffer(ctx.section()).map(SectionVisitor.visit)
      val defaultStyles = collectDefaultStyles(ctx.defaultStyle())
      Page(ctx.name.getText, sections, defaultStyles)
    }
  }

  object SectionVisitor extends QLSBaseVisitor[Section] {
    override def visitSection(ctx: QLSParser.SectionContext): Section = {
      val questions = JavaConverters.asScalaBuffer(ctx.questionBlock().questions).map(QuestionVisitor.visit)
      val defaultStyles = collectDefaultStyles(ctx.questionBlock().defaultStyle())
      Section(ctx.name.getText, questions, defaultStyles)
    }
  }

  object QuestionVisitor extends QLSBaseVisitor[Question] {
    override def visitQuestion(ctx: QLSParser.QuestionContext): Question = {
      if (ctx.styling() == null) Question(ctx.name.getText, Styling())
      else Question(ctx.name.getText, StylingVisitor.visit(ctx.styling()))
    }
  }

  private def collectDefaultStyles(
      defaultStyleContexts: util.List[QLSParser.DefaultStyleContext]): Map[AnswerType, Styling] = {
    val emptyMap: Map[AnswerType, Styling] = Map.empty
    JavaConverters.asScalaBuffer(defaultStyleContexts).map(DefaultStyleVisitor.visit).foldLeft(emptyMap)(_ ++ _)
  }

  object DefaultStyleVisitor extends QLSBaseVisitor[Map[AnswerType, Styling]] {
    override def visitDefaultStyle(ctx: QLSParser.DefaultStyleContext): Map[AnswerType, Styling] =
      Map(AnswerType(ctx.questionType().getText) -> StylingVisitor.visit(ctx.styling()))
  }

  object StylingVisitor extends QLSBaseVisitor[Styling] {
    override def visitStyling(ctx: QLSParser.StylingContext): Styling = {
      JavaConverters.asScalaBuffer(ctx.style()).map(StyleVisitor.visit).foldLeft(Styling())(_ ++ _)
    }
  }

  object StyleVisitor extends QLSBaseVisitor[Styling] {
    override def defaultResult(): Styling = Styling()
    override def visitWidgetStyling(ctx: QLSParser.WidgetStylingContext): Styling =
      Styling(WidgetTypeVisitor.visit(ctx.widgetType()), None, None, None, None)
    override def visitWidthStyling(ctx: QLSParser.WidthStylingContext): Styling =
      Styling(None, Some(Width(ctx.widthValue.getText.toInt)), None, None, None)
    override def visitColorStyling(ctx: QLSParser.ColorStylingContext): Styling =
      Styling(None, None, Some(Color(ctx.colorValue.getText)), None, None)
    override def visitFontTypeStyling(ctx: QLSParser.FontTypeStylingContext): Styling =
      Styling(None, None, None, Some(FontType(ctx.fontType.getText)), None)
    override def visitFontSizeStyling(ctx: QLSParser.FontSizeStylingContext): Styling =
      Styling(None, None, None, None, Some(FontSize(ctx.fontSize.getText.toInt)))
  }

  object WidgetTypeVisitor extends QLSBaseVisitor[Option[WidgetType]] {
    override def defaultResult(): Option[WidgetType] = None
    override def visitCheckBox(ctx: QLSParser.CheckBoxContext): Option[WidgetType] = Some(CheckBox())
    override def visitSpinBox(ctx: QLSParser.SpinBoxContext): Option[WidgetType] = Some(SpinBox())
    override def visitRadioButtons(ctx: QLSParser.RadioButtonsContext): Option[WidgetType] =
      Some(Radio(ctx.trueValue.getText, ctx.falseValue.getText))
    override def visitComboBox(ctx: QLSParser.ComboBoxContext): Option[WidgetType] =
      Some(ComboBox(ctx.trueValue.getText, ctx.falseValue.getText))
  }
}
