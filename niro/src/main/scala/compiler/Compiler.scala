package compiler

import model.Ast.Expression.Ident
import model.Ast.Expression.Operator.{ArithmOp, CompOp, LogicalOp, UnaryOp}
import model.Ast.Expression.Operator.ArithmOp.Mul
import model.Ast._
import nl.uva.se.sc.niro.ErrorListener
import ql.{QLBaseVisitor, QLLexer, QLParser}
import org.antlr.v4.runtime.{CharStream, CommonTokenStream}

import scala.collection.{JavaConverters, mutable}

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
    override def visitUnaryExpr(ctx: QLParser.UnaryExprContext): Expression = {
      UnaryOp(ctx.unaryOp().getText, visit(ctx.expression))
    }

    override def visitArithmExpr(ctx: QLParser.ArithmExprContext): Expression = {
      ArithmOp(ctx.arithmOp().getText, visit(ctx.lhs), visit(ctx.rhs))
    }

    override def visitCompExpr(ctx: QLParser.CompExprContext): Expression = {
      CompOp(ctx.compOp().getText, visit(ctx.lhs), visit(ctx.rhs))
    }

    override def visitLogicalExpr(ctx: QLParser.LogicalExprContext): Expression = {
      LogicalOp(ctx.logicalOp().getText, visit(ctx.lhs), visit(ctx.rhs))
    }
  }

}
