package nl.uva.se.sc.niro.parser

import java.util

import nl.uva.se.sc.niro.model.Expressions._
import nl.uva.se.sc.niro.model.Expressions.answers.{ BooleanAnswer, DecAnswer, IntAnswer, StringAnswer }
import nl.uva.se.sc.niro.model._
import org.antlr.v4.runtime.{ CharStream, CommonTokenStream, ParserRuleContext }
import org.apache.logging.log4j.scala.Logging
import _root_.ql.{ QLBaseVisitor, QLLexer, QLParser }
import org.antlr.v4.runtime.tree.RuleNode

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
      val formName = ctx.Identifier().getText
      val statements: Seq[Statement] = JavaConverters.asScalaBuffer(ctx.statement).toList.flatMap(StatementCompiler.visit)

      QLForm(formName, statements)
    }
  }

  object StatementCompiler extends QLBaseVisitor[Seq[Statement]] {
    override def defaultResult(): Seq[Statement] = Seq(ErrorStatement())

    override def shouldVisitNextChild(node: RuleNode, currentResult: Seq[Statement]): Boolean = {
      errorListener.getParseErrors.isEmpty
    }

    override def visitQuestion(ctx: QLParser.QuestionContext): Seq[Statement] = {
      val questionId = ctx.Identifier().getText
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
    override def visitGroupExpr(ctx: QLParser.GroupExprContext): Expression = {
      visit(ctx.expression())
    }
    override def visitUnaryExpr(ctx: QLParser.UnaryExprContext): Expression = {
      UnaryOperation(UnaryOperator(ctx.op.getText), visit(ctx.expression))
    }
    override def visitMultiplicativeExpr(ctx: QLParser.MultiplicativeExprContext): Expression = {
      BinaryOperation(BinaryOperator(ctx.op.getText), visit(ctx.lhs), visit(ctx.rhs))
    }
    override def visitAdditiveExpr(ctx: QLParser.AdditiveExprContext): Expression = {
      BinaryOperation(BinaryOperator(ctx.op.getText), visit(ctx.lhs), visit(ctx.rhs))
    }
    override def visitRelationalExp(ctx: QLParser.RelationalExpContext): Expression = {
      BinaryOperation(BinaryOperator(ctx.op.getText), visit(ctx.lhs), visit(ctx.rhs))
    }
    override def visitEqualityExpr(ctx: QLParser.EqualityExprContext): Expression = {
      BinaryOperation(BinaryOperator(ctx.op.getText), visit(ctx.lhs), visit(ctx.rhs))
    }
    override def visitLogicalAndExpr(ctx: QLParser.LogicalAndExprContext): Expression = {
      BinaryOperation(BinaryOperator(ctx.op.getText), visit(ctx.lhs), visit(ctx.rhs))
    }
    override def visitLogicalOrExpr(ctx: QLParser.LogicalOrExprContext): Expression = {
      BinaryOperation(BinaryOperator(ctx.op.getText), visit(ctx.lhs), visit(ctx.rhs))
    }
    override def visitIntConst(ctx: QLParser.IntConstContext): Expression = {
      IntAnswer(ctx.IntValue().getText.toInt)
    }
    override def visitDecConst(ctx: QLParser.DecConstContext): Expression = {
      DecAnswer(BigDecimal(ctx.DecValue().getText))
    }

    override def visitStringConst(ctx: QLParser.StringConstContext): Expression = {
      StringAnswer(ctx.TEXT().getText)
    }
    override def visitBool(ctx: QLParser.BoolContext): Expression = {
      BooleanAnswer(ctx.getText.toBoolean)
    }
    override def visitVar(ctx: QLParser.VarContext): Expression = {
      Reference(ctx.Identifier().getText)
    }
  }

}
