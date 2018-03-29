package ql.listeners

import grammar._
import org.antlr.v4.runtime._
import org.antlr.v4.runtime.tree._;
import scala.util.Try

class CountNodesListener extends QLBaseListener {
  val node_count = collection.mutable.Map[String, Int]().withDefaultValue(0)

  override def enterQuestion(ctx: QLParser.QuestionContext): Unit = {
    node_count("questions") += 1;
  }

  override def enterRoot(ctx: QLParser.RootContext): Unit = {
    node_count("root") += 1;
  }

  override def enterTypeDecl(ctx: QLParser.TypeDeclContext): Unit = {
    node_count("typeDecl") += 1;
  }

  override def enterForm(ctx: QLParser.FormContext): Unit = {
    node_count("form") += 1;
  }

  override def enterFormHeader(ctx: QLParser.FormHeaderContext): Unit = {
    node_count("formHeader") += 1;
  }

  override def enterVarDecl(ctx: QLParser.VarDeclContext): Unit = {
    node_count("varDecl") += 1;
  }

  override def enterIfStmt(ctx: QLParser.IfStmtContext): Unit = {
    node_count("ifStmt") += 1;
  }

  override def enterComputation(ctx: QLParser.ComputationContext): Unit = {
    node_count("computation") += 1;
  }

  override def enterBinOp(ctx: QLParser.BinOpContext): Unit = {
    node_count("binOp") += 1;
  }
}
