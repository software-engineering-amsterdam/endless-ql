package compiler

import model.Ast._
import nl.uva.se.sc.niro.ErrorListener
import ql.{QLBaseVisitor, QLLexer, QLParser}
import org.antlr.v4.runtime.{CharStream, CommonTokenStream}
import ql.QLParser.StatementContext

import scala.collection.JavaConverters

object Compiler extends QLBaseVisitor[Node] {

  def compile(formDefinition: CharStream): Node = {
    val parser = new QLParser(new CommonTokenStream(new QLLexer(formDefinition)))
    parser.removeErrorListeners()
    parser.addErrorListener(new ErrorListener)
    visit(parser.form)
  }

  override def visitForm(ctx: QLParser.FormContext): Node = {
    val statements = JavaConverters.asScalaBufferConverter(ctx.statement).asScala.toSeq.map(stmt => visitStatement(stmt).asInstanceOf[Statement])
    new QLForm(ctx.name().getText(), statements)
  }

  override def visitQuestion(ctx: QLParser.QuestionContext): Node = {
    new Question(ctx.name().getText, ctx.TEXT().getText, AnswerType.apply(ctx.answer_type().getText))
  }

}
