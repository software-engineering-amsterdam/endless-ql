package qls.visitors

import grammar._

import qls.models.ast._
import ql.models.ast.{ Identifier, StringValue, Expression }

import scala.collection.JavaConversions._

class ExpressionVisitor extends QLSBaseVisitor[Expression] {

  override def visitIdentifier(
      ctx: QLSParser.IdentifierContext): Identifier = {
    Identifier(ctx.getText)
  }

  override def visitTitle(ctx: QLSParser.TitleContext): StringValue = {
    StringValue(ctx.getText.replace("\"", ""))
  }
}
