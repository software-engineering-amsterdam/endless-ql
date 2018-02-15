package compiler

import model.Ast.Expression.Ident
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
      QLForm(ctx.Ident().getText, statements.map(StatementCompiler.visit))
    }
  }

  object StatementCompiler extends QLBaseVisitor[Statement] {
    override def visitQuestion(ctx: QLParser.QuestionContext): Statement = {
      Question(Ident(ctx.Ident().getText), ctx.TEXT().getText, AnswerType(ctx.answerType().getText))
    }

    override def visitConditional(ctx: QLParser.ConditionalContext): Statement = {
      val thenStatements = JavaConverters.asScalaBufferConverter(ctx.thenBlock).asScala
      val elseStatements = JavaConverters.asScalaBufferConverter(ctx.elseBlock).asScala
      Conditional(ExpressionCompiler.visit(ctx.condition), thenStatements.map(StatementCompiler.visit), elseStatements.map(StatementCompiler.visit))
    }
  }

  object ExpressionCompiler extends QLBaseVisitor[Expression] {
  }

}
