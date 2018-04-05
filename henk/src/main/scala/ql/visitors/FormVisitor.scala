package ql.visitors

import grammar._
import ql.models.ast._

import scala.collection.JavaConversions._

class FormVisitor extends QLBaseVisitor[FormElement] {

  val statementVisitor = new StatementVisitor()
  val expressionVisitor = new ExpressionVisitor()

  override def visitRoot(ctx: QLParser.RootContext): Root = {
    val body = visitFormBody(ctx.formBody)
    val header = visitFormHeader(ctx.formHeader)
    Root(header, body)
  }

  override def visitFormHeader(
      ctx: QLParser.FormHeaderContext): Header = {
    Header(expressionVisitor.visitIdentifier(ctx.identifier))
  }


  override def visitFormBody(ctx: QLParser.FormBodyContext): Body = {
    val statements = ctx.stmt.map(statementVisitor.visit).toList
    Body(statements)
  }
}
