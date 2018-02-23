package ql.listeners

import ql.grammar._
import org.antlr.v4.runtime._
import org.antlr.v4.runtime.tree._;
import scala.util.Try

class CountNodesListener extends QlParserBaseListener {
  val node_count = collection.mutable.Map[String, Int]().withDefaultValue(0)

  override def enterQuestion(ctx: QlParser.QuestionContext): Unit = {
    node_count("questions") += 1;
  }

  override def enterRoot(ctx: QlParser.RootContext): Unit = {
    node_count("root") += 1;
  }

  override def enterTypeDecl(ctx: QlParser.TypeDeclContext): Unit = {
    node_count("typeDecl") += 1;
  }

  override def enterForm(ctx: QlParser.FormContext): Unit = {
    node_count("form") += 1;
  }

  override def enterFormHeader(ctx: QlParser.FormHeaderContext): Unit = {
    node_count("formHeader") += 1;
  }

  override def enterVarDecl(ctx: QlParser.VarDeclContext): Unit = {
    node_count("varDecl") += 1;
  }

  override def enterIfStmt(ctx: QlParser.IfStmtContext): Unit = {
    node_count("ifStmt") += 1;
  }

  override def enterIfBody(ctx: QlParser.IfBodyContext): Unit = {
    node_count("ifBody") += 1;
  }

  override def enterOperation(ctx: QlParser.OperationContext): Unit = {
    node_count("operation") += 1;
  }
}
