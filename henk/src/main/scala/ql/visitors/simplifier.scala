package ql.visitors

import ql.grammar._
import ql.models._

import scala.collection.JavaConversions._

class SimplifierVisitor extends QlParserBaseVisitor[ASTNode] {

  override def visitRoot(ctx: QlParser.RootContext): ASTRoot = {
    val body = visitFormBody(ctx.formBody)
    val header = visit(ctx.formHeader)
    ASTRoot(header, body)
  }

  override def visitFormHeader(ctx: QlParser.FormHeaderContext): ASTFormHeader = {
    ASTFormHeader()
  }

  override def visitFormBody(ctx: QlParser.FormBodyContext): ASTFormBody = {
    val statements = ctx.stmt.map(visit).toList
    ASTFormBody(statements)
  }

  override def visitQuestion(ctx: QlParser.QuestionContext): ASTQuestion = {
    // prease move this abomination to a 'pre' clean-up visitor.
    // val label = ctx.label.getText.replace("\"", "")
    val varDecl = visit(ctx.varDecl)
    ASTQuestion(varDecl)
  }

  override def visitComputation(ctx: QlParser.ComputationContext): ASTComputation = {
    ASTComputation(visit(ctx.varDecl), visit(ctx.valAssign))
  }

  override def visitVarDecl(ctx: QlParser.VarDeclContext): ASTVarDecl = {
    ASTVarDecl(visit(ctx.typeDecl))
  }

  override def visitTypeDecl(ctx: QlParser.TypeDeclContext): ASTNode = ctx.getText match {
      case "boolean" => ASTBoolean()
      case "money" => ASTMoney()
  }

  override def visitConditional(ctx: QlParser.ConditionalContext): ASTNode = {
    visit(ctx.ifStmt)
    // ASTConditional()
  }

  override def visitValAssign(ctx: QlParser.ValAssignContext): ASTNode = {
    ASTValAssign(visit(ctx.expr))
  }

  override def visitBinOp(ctx: QlParser.BinOpContext): ASTNode = ctx.getText match {
      case "+" => ASTAdd()
      case "-" => ASTMin()
      case "*" => ASTMul()
      case "/" => ASTDiv()
  }

  override def visitUnOp(ctx: QlParser.UnOpContext): ASTNode = {
    ASTNegate()
  }

  override def visitIdentifierExpression(ctx: QlParser.IdentifierExpressionContext): ASTNode = {
    ASTIdentifier()
  }

  override def visitBinaryExpression(ctx: QlParser.BinaryExpressionContext): ASTNode = {
    ASTBinary(visit(ctx.lhs), visit(ctx.rhs), visit(ctx.binOp))
  }

  override def visitUnaryExpression(ctx: QlParser.UnaryExpressionContext): ASTNode = {
    ASTUnary(visit(ctx.expr), visit(ctx.op))
  }

  override def visitBracketExpression(ctx: QlParser.BracketExpressionContext): ASTNode = {
    visit(ctx.expr)
  }

  override def visitIfStmt(ctx: QlParser.IfStmtContext): ASTIfStatement = {
    val statements = ctx.stmt.map(visit).toList
    ASTIfStatement(visit(ctx.expr), statements)
  }
}
