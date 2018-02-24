package ql.visitors

import ql.grammar._
import ql.models._

import scala.collection.JavaConversions._

class SimplifierVisitor extends QlParserBaseVisitor[ASTNode] {

  override def visitRoot(ctx: QlParser.RootContext): ASTRoot = {
    val body = visit(ctx.formBody)
    val header = visit(ctx.formHeader)
    ASTRoot(header, body)
  }

  override def visitFormHeader(ctx: QlParser.FormHeaderContext): ASTFormHeader = {
    ASTFormHeader(ctx.IDENTIFIER.getText)
  }

  override def visitFormBody(ctx: QlParser.FormBodyContext): ASTFormBody = {
    val statements = ctx.stmt.map(visit).toList
    ASTFormBody(statements)
  }

  override def visitQuestion(ctx: QlParser.QuestionContext): ASTQuestion = {
    // prease move this abomination to a 'pre' clean-up visitor.
    val label = ctx.label.getText.replace("\"", "")
    val varDecl = visit(ctx.varDecl)
    ASTQuestion(label, varDecl)
  }

  override def visitVarDecl(ctx: QlParser.VarDeclContext): ASTVarDecl = {
    ASTVarDecl(ctx.IDENTIFIER.getText, visit(ctx.typeDecl), null)
  }

  override def visitTypeDecl(ctx: QlParser.TypeDeclContext) = ctx.getText match {
      case "boolean" => ASTBoolean()
  }

  override def visitConditional(ctx: QlParser.ConditionalContext): ASTConditional = {
    ASTConditional()
  }
}
