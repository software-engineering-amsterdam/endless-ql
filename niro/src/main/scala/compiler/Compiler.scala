package compiler

import model.Ast._
import nl.uva.se.sc.niro.ErrorListener
import ql.{QLBaseVisitor, QLLexer, QLParser}
import org.antlr.v4.runtime.{CharStream, CommonTokenStream}

import scala.collection.JavaConverters

object Compiler {

  def compile(formSource: CharStream): QLForm = {
    val parser = new QLParser(new CommonTokenStream(new QLLexer(formSource)))
    parser.removeErrorListeners()
    parser.addErrorListener(new ErrorListener)
    FormCompiler.visit(parser.form)
  }

  object FormCompiler extends QLBaseVisitor[QLForm] {
    override def visitForm(ctx: QLParser.FormContext): QLForm = {
      val statements = JavaConverters.asScalaBufferConverter(ctx.statement).asScala
      QLForm(ctx.Ident().getText, statements.map(stmt => StatementCompiler.visit(stmt)))
    }
  }

  object StatementCompiler extends QLBaseVisitor[Statement] {
    override def visitQuestion(ctx: QLParser.QuestionContext): Statement = {
      Question(ctx.Ident().getText, ctx.TEXT().getText, AnswerType(ctx.answerType().getText))
    }

    override def visitConditional(ctx: QLParser.ConditionalContext): Statement = {
      Conditional()
    }
  }

}
