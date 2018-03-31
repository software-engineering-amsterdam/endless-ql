package nl.uva.se.sc.niro.parser

import java.util

import nl.uva.se.sc.niro.errors.Errors.Error
import nl.uva.se.sc.niro.model.ql.AnswerType
import nl.uva.se.sc.niro.model.qls._
import nl.uva.se.sc.niro.model.qls.style._
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
      val sections = JavaConverters.asScalaBuffer(ctx.sections).map(SectionVisitor.visit)
      val defaultStyles = collectDefaultStyles(ctx.defaultStyle())
      Page(ctx.name.getText, sections, defaultStyles)
    }
  }

  object SectionVisitor extends QLSBaseVisitor[Section] {
    override def visitMultiStatementSection(ctx: QLSParser.MultiStatementSectionContext): Section = {
      val statements = JavaConverters.asScalaBuffer(ctx.statements).map(StatementVisitor.visit)
      val defaultStyles = collectDefaultStyles(ctx.defaultStyle())
      Section(ctx.name.getText, statements, defaultStyles)
    }

    override def visitSingleStatementSection(ctx: QLSParser.SingleStatementSectionContext): Section = {
      val statement = StatementVisitor.visit(ctx.statement())
      Section(ctx.name.getText, Seq(statement), Map.empty)
    }
  }

  object StatementVisitor extends QLSBaseVisitor[Statement] {
    override def visitQuestionStatement(ctx: QLSParser.QuestionStatementContext): Statement = {
      val styling = if (ctx.question().styling() != null) StylingVisitor.visit(ctx.question().styling()) else Styling()
      Question(ctx.question().name.getText, styling)
    }

    override def visitSectionStatement(ctx: QLSParser.SectionStatementContext): Statement =
      SectionVisitor.visit(ctx.section())
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
    override def visitSpinBox(ctx: QLSParser.SpinBoxContext): Option[WidgetType] = {
      val minimum = if (ctx.minimum == null) 0 else ctx.minimum.getText.toDouble
      val maximum = if (ctx.maximum == null) 0 else ctx.maximum.getText.toDouble
      val stepSize = if (ctx.stepSize == null) 0 else ctx.stepSize.getText.toDouble
      Some(SpinBox(minimum, maximum, stepSize))
    }
    override def visitSlider(ctx: QLSParser.SliderContext): Option[WidgetType] = {
      val minimum = if (ctx.minimum == null) 0 else ctx.minimum.getText.toDouble
      val maximum = if (ctx.maximum == null) 0 else ctx.maximum.getText.toDouble
      Some(Slider(minimum, maximum))
    }
    override def visitRadioButtons(ctx: QLSParser.RadioButtonsContext): Option[WidgetType] =
      Some(Radio(ctx.trueValue.getText, ctx.falseValue.getText))
    override def visitComboBox(ctx: QLSParser.ComboBoxContext): Option[WidgetType] =
      Some(ComboBox(ctx.trueValue.getText, ctx.falseValue.getText))
  }

  private def collectDefaultStyles(
      defaultStyleContexts: util.List[QLSParser.DefaultStyleContext]): Map[AnswerType, Styling] = {
    val emptyMap: Map[AnswerType, Styling] = Map.empty
    JavaConverters.asScalaBuffer(defaultStyleContexts).map(DefaultStyleVisitor.visit).foldLeft(emptyMap)(_ ++ _)
  }

}
