package qls.visitors

import grammar._

import ql.models.ast.{ BooleanType, StringType, IntegerType }
import qls.models.ast._

import scala.collection.JavaConversions._

class ElementVisitor extends QLSBaseVisitor[DisplayItem] {

  val expressionVisitor = new ExpressionVisitor()
  val styleVisitor = new StyleVisitor()

  override def visitSection(ctx: QLSParser.SectionContext): DisplayItem = {
    val header = ctx.sectionHeader
    val body = ctx.element.map(visit).toList
    Section(expressionVisitor.visitTitle(header.title), body)
  }

  override def visitQuestion(ctx: QLSParser.QuestionContext): Question = {

    val styling = Option(ctx.stylingDecl)
      .map(styleVisitor.visitStyling(_))

    val identifier = expressionVisitor.visitIdentifier(ctx.identifier)
    Question(identifier, styling)
  }

  override def visitDefaultDecl(
      ctx: QLSParser.DefaultDeclContext): DefaultDecl = {
    val styling = styleVisitor.visitStyling(ctx.stylingDecl)
    val defaultType = ctx.types.getText match {
      case "boolean" => BooleanType
      case "integer" => IntegerType
      case "string"  => StringType
    }
    DefaultDecl(defaultType, styling)
  }
}
