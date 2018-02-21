package nl.uva.se.sc.niro.parser

import java.util

import nl.uva.se.sc.niro.model.Ast._
import nl.uva.se.sc.niro.model.Expressions.Expression.{Answer, BinaryOperation, Expression, UnaryOperation}
import nl.uva.se.sc.niro.model.Expressions.answers.{BooleanAnswer, DecAnswer, IntAnswer, Reference}
import nl.uva.se.sc.niro.model.Operators.{BinaryOperator, UnaryOperator}
import org.antlr.v4.runtime.{CharStream, CommonTokenStream}
import org.apache.logging.log4j.Level
import org.apache.logging.log4j.scala.Logging
import ql.{QLBaseVisitor, QLLexer, QLParser}

import scala.collection.JavaConverters

object QLFormParser extends Logging {
  private val errorListener = new ErrorListener
  def getParseErrors: util.List[ParseErrorInfo] = errorListener.getParseErrors

  def parse(formSource: CharStream): QLForm = {
    logger.traceEntry()
    errorListener.getParseErrors.clear()
    val parser = new QLParser(new CommonTokenStream(new QLLexer(formSource)))
    parser.removeErrorListeners()
    parser.addErrorListener(errorListener)
    logger.traceExit(FormCompiler.visit(parser.form))
  }

  object FormCompiler extends QLBaseVisitor[QLForm] {
    override def visitForm(ctx: QLParser.FormContext): QLForm = {
      val statements = JavaConverters.asScalaBuffer(ctx.statement).toList
      QLForm(ctx.Ident().getText, statements.map(StatementCompiler.visit))
    }
  }

  object StatementCompiler extends QLBaseVisitor[Statement] {
    override def visitQuestion(ctx: QLParser.QuestionContext): Statement = {
      Question(ctx.Ident().getText, ctx.label.getText, ExpressionCompiler.visit(ctx.expression))
    }

    override def visitConditional(ctx: QLParser.ConditionalContext): Statement = {
      val thenStatements = JavaConverters.asScalaBuffer(ctx.thenBlock).toList
      val elseStatements = JavaConverters.asScalaBuffer(ctx.elseBlock).toList
      Conditional(ExpressionCompiler.visit(ctx.condition), thenStatements.map(StatementCompiler.visit), elseStatements.map(StatementCompiler.visit))
    }
  }

  object ExpressionCompiler extends QLBaseVisitor[Expression] {
    override def visitAnswerTypeConst(ctx: QLParser.AnswerTypeConstContext): Expression = {
      Answer(ctx.getText)
    }

    override def visitIntConst(ctx: QLParser.IntConstContext): Expression = {
      IntAnswer(Some(ctx.IntValue().getText.toInt))
    }

    override def visitDecConst(ctx: QLParser.DecConstContext): Expression = {
      DecAnswer(Some(ctx.DecValue().getText.toDouble))
    }

    override def visitBool(ctx: QLParser.BoolContext): Expression = {
      BooleanAnswer(Some(ctx.getText.toBoolean))
    }

    override def visitVar(ctx: QLParser.VarContext): Expression = {
      Reference(ctx.Ident().getText)
    }

    override def visitUnaryExpr(ctx: QLParser.UnaryExprContext): Expression = {
      UnaryOperation(UnaryOperator(ctx.unaryOp().getText), visit(ctx.expression))
    }

    override def visitArithmExpr(ctx: QLParser.ArithmExprContext): Expression = {
      BinaryOperation(BinaryOperator(ctx.arithmOp().getText), visit(ctx.lhs), visit(ctx.rhs))
    }

    override def visitCompExpr(ctx: QLParser.CompExprContext): Expression = {
      BinaryOperation(BinaryOperator(ctx.compOp().getText), visit(ctx.lhs), visit(ctx.rhs))
    }

    override def visitLogicalExpr(ctx: QLParser.LogicalExprContext): Expression = {
      BinaryOperation(BinaryOperator(ctx.logicalOp().getText), visit(ctx.lhs), visit(ctx.rhs))
    }

    override def visitGroupExpr(ctx: QLParser.GroupExprContext): Expression = {
      visit(ctx.expression())
    }
  }
}
