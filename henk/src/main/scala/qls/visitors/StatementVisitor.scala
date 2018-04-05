package qls.visitors

import grammar._
import qls.models.ast._

import scala.collection.JavaConversions._

class StatementVisitor extends QLSBaseVisitor[Statement] {

  val expressionVisitor = new ExpressionVisitor()
  val elementVisitor = new ElementVisitor()

  override def visitRoot(ctx: QLSParser.RootContext): Root = {
    val body = visitRootBody(ctx.rootBody)
    val header = visitRootHeader(ctx.rootHeader)
    Root(header, body)
  }

  override def visitRootHeader(
      ctx: QLSParser.RootHeaderContext): RootHeader = {
    RootHeader(expressionVisitor.visitIdentifier(ctx.identifier))
  }

  override def visitRootBody(ctx: QLSParser.RootBodyContext): RootBody = {
    val pages = ctx.page.map(visitPage).toList
    RootBody(pages)
  }

  override def visitPage(ctx: QLSParser.PageContext): Page = {
    val pageHeader = ctx.pageHeader
    val pageBody = ctx.pageBody.element.map(elementVisitor.visit(_)).toList
    Page(expressionVisitor.visitIdentifier(pageHeader.identifier), pageBody)
  }
}
