package nl.uva.se.sc.niro.parser

import java.util

import nl.uva.se.sc.niro.model.Expressions._
import nl.uva.se.sc.niro.model.Expressions.answers.{ BooleanAnswer, DecAnswer, IntAnswer }
import nl.uva.se.sc.niro.model._
import org.antlr.v4.runtime.{ CharStream, CommonTokenStream }
import org.apache.logging.log4j.scala.Logging
import _root_.ql.{ QLBaseVisitor, QLLexer, QLParser }

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
      val formName = ctx.Ident().getText
      val statements: Seq[Statement] = JavaConverters.asScalaBuffer(ctx.statement).toList.flatMap(StatementCompiler.visit)

      QLForm(formName, statements)
    }
  }

  object StatementCompiler extends QLBaseVisitor[Seq[Statement]] {
    override def visitQuestion(ctx: QLParser.QuestionContext): Seq[Statement] = {
      val questionId = ctx.Ident().getText
      val questionLabel = ctx.label.getText
      val expression = Option(ctx.expression)
        .map(ExpressionCompiler.visit)
        .getOrElse(Answer(ctx.answerType.getText))
      Seq(Question(questionId, questionLabel, expression))
    }

    override def visitConditional(ctx: QLParser.ConditionalContext): Seq[Statement] = {
      val predicate: Expression = ExpressionCompiler.visit(ctx.condition)
      val negatedPredicate: Expression = UnaryOperation(Neg, predicate)

      val thenStatements: Seq[Statement] = JavaConverters.asScalaBuffer(ctx.thenBlock).toList.flatMap(StatementCompiler.visit)
      val elseStatements: Seq[Statement] = JavaConverters.asScalaBuffer(ctx.elseBlock).toList.flatMap(StatementCompiler.visit)

      val ifConditional = Conditional(predicate, thenStatements)
      val elseConditional = Conditional(negatedPredicate, elseStatements)

      Seq(ifConditional, elseConditional).filter(_.thenStatements.nonEmpty)
    }
  }

  object ExpressionCompiler extends QLBaseVisitor[Expression] {
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
