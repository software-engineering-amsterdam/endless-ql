package ql.visitors

import grammar._
import ql.models.ast._

import scala.collection.JavaConversions._

class ASTVisitor extends QLBaseVisitor[ASTNode] {

  override def visitRoot(ctx: QLParser.RootContext): ASTRoot = {
    val body = visitFormBody(ctx.formBody)
    val header = visit(ctx.formHeader)
    ASTRoot(header, body)
  }

  override def visitFormHeader(
      ctx: QLParser.FormHeaderContext): ASTFormHeader = {
    ASTFormHeader(visit(ctx.identifier))
  }


  override def visitFormBody(ctx: QLParser.FormBodyContext): ASTFormBody = {
    val statements = ctx.stmt.map(visit).toList
    ASTFormBody(statements)
  }

  override def visitQuestion(ctx: QLParser.QuestionContext): ASTQuestion = {
    val varDecl = visit(ctx.varDecl)
    ASTQuestion(varDecl, ctx.label.getText.replace("\"", ""))
  }

  override def visitComputation(
      ctx: QLParser.ComputationContext): ASTComputation = {
    ASTComputation(visit(ctx.varDecl),
                   visit(ctx.valAssign),
                   ctx.label.getText.replace("\"", ""))
  }

  override def visitVarDecl(ctx: QLParser.VarDeclContext): ASTVarDecl = {
    ASTVarDecl(visit(ctx.typeDecl), visit(ctx.identifier))
  }

  override def visitTypeDecl(ctx: QLParser.TypeDeclContext): ASTNode =
    ctx.getText match {
      case "boolean" => ASTBoolean()
      case "money"   => ASTMoney()
      case "integer" => ASTInteger()
      case "string"  => ASTString()
    }

  override def visitConditional(ctx: QLParser.ConditionalContext): ASTNode = {
    visit(ctx.ifStmt)
  }

  override def visitIfStmt(ctx: QLParser.IfStmtContext): ASTIfStatement = {
    val statements = ctx.stmt.map(visit).toList
    ASTIfStatement(visit(ctx.expr), statements)
  }

  override def visitValAssign(ctx: QLParser.ValAssignContext): ASTNode = {
    ASTValAssign(visit(ctx.expr))
  }

  override def visitBinOp(ctx: QLParser.BinOpContext): ASTNode =
    ctx.getText match {
      case "+"  => ASTAdd()
      case "-"  => ASTMin()
      case "*"  => ASTMul()
      case "/"  => ASTDiv()
      case "!"  => ASTUnaryNot()
      case "&&" => ASTLogicalCon()
      case "||" => ASTLogicalDis()
      case "<"  => ASTRelationalLT()
      case ">"  => ASTRelationalGT()
      case "<=" => ASTRelationalLTE()
      case ">=" => ASTRelationalGTE()
      case "!=" => ASTNotEqualOp()
      case "==" => ASTEqualOp()
    }

  override def visitUnOp(ctx: QLParser.UnOpContext): ASTNode =
    ctx.getText match {
      case "-" => ASTUnaryMin()
      case "!" => ASTUnaryNot()
    }

  override def visitStringExpression(
      ctx: QLParser.StringExpressionContext): ASTNode = {
    visit(ctx.stringLit)
  }

  override def visitStringLit(
      ctx: QLParser.StringLitContext): ASTNode = {
    ASTStringValue(ctx.getText.replace("\"", ""))
  }

  override def visitBooleanExpression(
      ctx: QLParser.BooleanExpressionContext): ASTNode = {
    visit(ctx.booleanLit)
  }

  override def visitBooleanLit(
      ctx: QLParser.BooleanLitContext): ASTNode = {
    ASTBooleanValue(ctx.getText.toBoolean)
  }

  override def visitIntegerExpression(
      ctx: QLParser.IntegerExpressionContext): ASTNode = {
    visit(ctx.integerLit)
  }

  override def visitIntegerLit(
      ctx: QLParser.IntegerLitContext): ASTNode = {
    ASTIntegerValue(Integer.parseInt(ctx.getText))
  }

  override def visitIdentifier(
      ctx: QLParser.IdentifierContext): ASTIdentifier = {
    ASTIdentifier(ctx.getText)
  }

  override def visitIdentifierExpression(
      ctx: QLParser.IdentifierExpressionContext): ASTNode = {
    visit(ctx.identifier)
  }

  override def visitBinaryExpression(
      ctx: QLParser.BinaryExpressionContext): ASTNode = {
    ASTBinary(visit(ctx.lhs), visit(ctx.rhs), visit(ctx.binOp))
  }

  override def visitUnaryExpression(
      ctx: QLParser.UnaryExpressionContext): ASTNode = {
    ASTUnary(visit(ctx.expr), visit(ctx.op))
  }

  override def visitBracketExpression(
      ctx: QLParser.BracketExpressionContext): ASTNode = {
    visit(ctx.expr)
  }

}
