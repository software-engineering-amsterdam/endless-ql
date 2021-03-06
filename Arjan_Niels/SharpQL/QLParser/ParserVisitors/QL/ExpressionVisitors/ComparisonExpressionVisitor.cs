﻿using Antlr4.Runtime.Misc;
using QLGrammar;
using QLParser.AST;
using QLParser.AST.QL;
using QLParser.AST.QL.ExpressionNodes;
using System;
using static QLGrammar.QLGrammarParser;

namespace QLParser.ParserVisitors.QL.ExpressionVisitors
{
    public class ComparisonExpressionVisitor : QLGrammarBaseVisitor<IExpressionNode>
    {
        public override IExpressionNode VisitComparisonExpression(ComparisonExpressionContext context)
        {
            if (context == null)
                throw new ArgumentNullException("Context can't be null");

            var comparisonoContext = context.comparisonExpression();
            if (comparisonoContext != null)
                return VisitComparisonExpression(comparisonoContext);

            var left = VisitComparisonOperand(context.LEFT);
            var opr = ComparisonExpressionNode.ParseComparisonOperator(context.OPR.GetText());
            var right = VisitComparisonOperand(context.RIGHT);

            return new ComparisonExpressionNode(Location.FromContext(context), left, opr, right);
        }

        public override IExpressionNode VisitComparisonOperand([NotNull] ComparisonOperandContext context)
        {
            var arthimeticContext = context.artithmeticExpression();
            return new ArthimetricExpressionVisitor().VisitArtithmeticExpression(arthimeticContext);
        }
    }
}
