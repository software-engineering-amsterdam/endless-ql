package ql.visitors

import grammar._
import ql.models.ast._

import scala.collection.JavaConversions._

class ExpressionVisitor extends QLBaseVisitor[Expression] {

  override def visitBinaryExpression(
      ctx: QLParser.BinaryExpressionContext): Expression = {
    val lhs = visit(ctx.lhs)
    val rhs = visit(ctx.rhs)

    ctx.binOp.getText match {
      case "+"  => AddOp(lhs, rhs)
      case "-"  => MinOp(lhs, rhs)
      case "*"  => MulOp(lhs, rhs)
      case "/"  => DivOp(lhs, rhs)
      case "&&" => LogicalConOp(lhs, rhs)
      case "||" => LogicalDisOp(lhs, rhs)
      case "<"  => RelationalLTOp(lhs, rhs)
      case ">"  => RelationalGTOp(lhs, rhs)
      case "<=" => RelationalLTEOp(lhs, rhs)
      case ">=" => RelationalGTEOp(lhs, rhs)
      case "!=" => NotEqualOp(lhs, rhs)
      case "==" => EqualOp(lhs, rhs)
    }
  }

  override def visitUnaryExpression(ctx: QLParser.UnaryExpressionContext): Expression = {
    val expr = visit(ctx.expr)
    ctx.unOp.getText match {
      case "-" => UnaryMinOp(expr)
      case "!" => UnaryNotOp(expr)
    }
  }

  override def visitIntegerExpression(
      ctx: QLParser.IntegerExpressionContext): Expression = {
    val integerLit = ctx.integerLit
    IntegerValue(Integer.parseInt(integerLit.getText))
  }

  override def visitBooleanExpression(
      ctx: QLParser.BooleanExpressionContext): Expression = {
    val booleanLit = ctx.booleanLit
    BooleanValue(booleanLit.getText.toBoolean)
  }

  override def visitStringExpression(
      ctx: QLParser.StringExpressionContext): Expression = {
    val stringLit = ctx.stringLit
    StringValue(stringLit.getText.replace("\"", ""))
  }

  override def visitIdentifier(
      ctx: QLParser.IdentifierContext): Identifier = {
    Identifier(ctx.getText)
  }

  override def visitIdentifierExpression(
      ctx: QLParser.IdentifierExpressionContext): Expression = {
    visit(ctx.identifier)
  }

  override def visitBracketExpression(
      ctx: QLParser.BracketExpressionContext): Expression = {
    visit(ctx.expr)
  }
}
