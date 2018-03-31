package qls.visitors

import grammar._
import qls.models.ast._

import scala.collection.JavaConversions._

class OptionValuesVisitor extends QLSBaseVisitor[List[Expression]] {

  override def visitStringOptions(ctx: QLSParser.StringOptionsContext): List[Expression] = {
    ctx.STRING_LIT.map(x => {
      StringValue(x.getText.replace("\"", ""))
    }).toList
  }

  override def visitPolarOptions(ctx: QLSParser.PolarOptionsContext): List[Expression] = {
    ctx.POLAR_LIT.map(x => {
      PolarValue(x.getText.replace("\"", ""))
    }).toList
  }

  override def visitIntegerOptions(ctx: QLSParser.IntegerOptionsContext): List[Expression] = {
    ctx.INTEGER_LIT.map(x => {
      IntegerValue(x.getText.toInt)
    }).toList
  }
}
