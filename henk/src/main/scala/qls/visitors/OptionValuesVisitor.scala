package qls.visitors

import grammar._

import qls.models.ast._
import ql.models.ast.{ ExpressionValue, StringValue, BooleanValue, IntegerValue }

import scala.collection.JavaConversions._

class OptionValuesVisitor extends QLSBaseVisitor[List[ExpressionValue]] {

  override def visitStringOptions(ctx: QLSParser.StringOptionsContext): List[ExpressionValue] = {
    ctx.STRING_LIT.map(x => {
      StringValue(x.getText.replace("\"", ""))
    }).toList
  }

  override def visitPolarOptions(ctx: QLSParser.PolarOptionsContext): List[ExpressionValue] = {
    List(BooleanValue(false), BooleanValue(true))
  }

  override def visitIntegerOptions(ctx: QLSParser.IntegerOptionsContext): List[ExpressionValue] = {
    ctx.INTEGER_LIT.map(x => {
      IntegerValue(x.getText.toInt)
    }).toList
  }
}
