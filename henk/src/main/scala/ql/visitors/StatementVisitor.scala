package ql.visitors

import grammar._
import ql.models.ast._

import scala.collection.JavaConversions._

class StatementVisitor extends QLBaseVisitor[Statement] {

  val expressionVisitor = new ExpressionVisitor()
  val typeVisitor = new TypeVisitor()

  override def visitRoot(ctx: QLParser.RootContext): Statement = {
    val body = visit(ctx.formBody)
    val header = visit(ctx.formHeader)
    Root(header, body)
  }

  override def visitFormHeader(
      ctx: QLParser.FormHeaderContext): Statement = {
    FormHeader(expressionVisitor.visit(ctx.identifier))
  }


  override def visitFormBody(ctx: QLParser.FormBodyContext): Statement = {
    val statements = ctx.stmt.map(visit).toList
    FormBody(statements)
  }

  override def visitQuestion(ctx: QLParser.QuestionContext): Statement = {
    val varDecl = visitVarDecl(ctx.varDecl)
    Question(varDecl, ctx.label.getText.replace("\"", ""))
  }

  override def visitComputation(ctx: QLParser.ComputationContext): Statement = {
    val varDecl = visitVarDecl(ctx.varDecl)
    val valAssign = visitValAssign(ctx.valAssign)
    Computation(varDecl, valAssign, ctx.label.getText.replace("\"", ""))
  }

  override def visitVarDecl(ctx: QLParser.VarDeclContext): VarDecl = {
    val typeDecl = typeVisitor.visit(ctx.typeDecl)
    val identifier = expressionVisitor.visit(ctx.identifier)
    VarDecl(typeDecl, identifier)
  }

  override def visitConditional(ctx: QLParser.ConditionalContext): Statement = {
    visit(ctx.ifStmt)
  }

  override def visitIfStmt(ctx: QLParser.IfStmtContext): Statement = {
    val statements = ctx.stmt.map(visit).toList
    val predicate = expressionVisitor.visit(ctx.expr)
    IfStatement(predicate, statements)
  }

  override def visitValAssign(ctx: QLParser.ValAssignContext): ValAssign = {
    val expression = expressionVisitor.visit(ctx.expr)
    ValAssign(expression)
  }

  // override def visitBinOp(ctx: QLParser.BinOpContext): ASTNode =
    // ctx.getText match {
      // case "+"  => ASTAdd()
      // case "-"  => ASTMin()
      // case "*"  => ASTMul()
      // case "/"  => ASTDiv()
      // case "!"  => ASTUnaryNot()
      // case "&&" => ASTLogicalCon()
      // case "||" => ASTLogicalDis()
      // case "<"  => ASTRelationalLT()
      // case ">"  => ASTRelationalGT()
      // case "<=" => ASTRelationalLTE()
      // case ">=" => ASTRelationalGTE()
      // case "!=" => ASTNotEqualOp()
      // case "==" => ASTEqualOp()
    // }

  // override def visitUnOp(ctx: QLParser.UnOpContext): ASTNode =
    // ctx.getText match {
      // case "-" => ASTUnaryMin()
      // case "!" => ASTUnaryNot()
    // }

  // override def visitStringExpression(
      // ctx: QLParser.StringExpressionContext): ASTNode = {
    // visit(ctx.stringLit)
  // }

  // override def visitStringLit(
      // ctx: QLParser.StringLitContext): ASTNode = {
    // ASTStringValue(ctx.getText.replace("\"", ""))
  // }

  // override def visitBooleanExpression(
      // ctx: QLParser.BooleanExpressionContext): ASTNode = {
    // visit(ctx.booleanLit)
  // }

  // override def visitBooleanLit(
      // ctx: QLParser.BooleanLitContext): ASTNode = {
    // ASTBooleanValue(ctx.getText.toBoolean)
  // }

  // override def visitIntegerExpression(
      // ctx: QLParser.IntegerExpressionContext): ASTNode = {
    // visit(ctx.integerLit)
  // }

  // override def visitIntegerLit(
      // ctx: QLParser.IntegerLitContext): ASTNode = {
    // ASTIntegerValue(Integer.parseInt(ctx.getText))
  // }

  // override def visitIdentifier(
      // ctx: QLParser.IdentifierContext): ASTIdentifier = {
    // ASTIdentifier(ctx.getText)
  // }

  // override def visitIdentifierExpression(
      // ctx: QLParser.IdentifierExpressionContext): ASTNode = {
    // visit(ctx.identifier)
  // }

  // override def visitBinaryExpression(
      // ctx: QLParser.BinaryExpressionContext): ASTNode = {
    // ASTBinary(visit(ctx.lhs), visit(ctx.rhs), visit(ctx.binOp))
  // }

  // override def visitUnaryExpression(
      // ctx: QLParser.UnaryExpressionContext): ASTNode = {
    // ASTUnary(visit(ctx.expr), visit(ctx.op))
  // }

  // override def visitBracketExpression(
      // ctx: QLParser.BracketExpressionContext): ASTNode = {
    // visit(ctx.expr)
  // }

}
