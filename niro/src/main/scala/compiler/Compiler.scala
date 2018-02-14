package compiler

import model.Ast._
import nl.uva.se.sc.niro.ErrorListener
import ql.{QLBaseVisitor, QLLexer, QLParser}
import org.antlr.v4.runtime.{CharStream, CommonTokenStream}

import scala.collection.JavaConverters

object Compiler extends QLBaseVisitor[Node] {

  def compile(formSource: CharStream): Node = {
    val parser = new QLParser(new CommonTokenStream(new QLLexer(formSource)))
    parser.removeErrorListeners()
    parser.addErrorListener(new ErrorListener)
    visit(parser.form)
  }

  override def visitForm(ctx: QLParser.FormContext): Node = {
    val statements = JavaConverters.asScalaBufferConverter(ctx.statement).asScala
    new QLForm(ctx.Ident().getText(), statements.map(stmt => visitStatement(stmt).asInstanceOf[Statement]))
  }

  override def visitQuestion(ctx: QLParser.QuestionContext): Node = {
    new Question(ctx.Ident().getText, ctx.TEXT().getText, AnswerType.apply(ctx.answerType().getText))
  }

}
