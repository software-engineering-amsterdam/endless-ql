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
    val identifier = expressionVisitor.visitIdentifier(ctx.identifier)
    VarDecl(typeDecl, identifier)
  }

  override def visitConditional(ctx: QLParser.ConditionalContext): Statement = {
    val ifStmt = visit(ctx.ifStmt)
    val elseStmt = Option(ctx.elseStmt).map(visitElseStmt)
    ConditionalStatement(ifStmt, elseStmt)
  }

  override def visitIfStmt(ctx: QLParser.IfStmtContext): Statement = {
    val statements = ctx.stmt.map(visit).toList
    val predicate = expressionVisitor.visit(ctx.expr)
    IfStatement(predicate, statements)
  }

  override def visitElseStmt(ctx: QLParser.ElseStmtContext): Statement = {
    val statements = ctx.stmt.map(visit).toList
    ElseStatement(statements)
  }

  override def visitValAssign(ctx: QLParser.ValAssignContext): ValAssign = {
    val expression = expressionVisitor.visit(ctx.expr)
    ValAssign(expression)
  }
}
