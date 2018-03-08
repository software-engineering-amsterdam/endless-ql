using Antlr4.Runtime.Misc;
using QLParser.AST.Nodes;
using QLParser.AST.Nodes.ExpressionNodes;
using QLParser.AST.Nodes.ExpressionNodes.Enums;
using QLParser.Exceptions;
using QLanguage;

namespace QLParser.Visitors.ExpressionVisitors
{
    public class ArthimetricExpressionVisitor : QLanguage.QLanguageBaseVisitor<IExpressionNode>
    {
        public override IExpressionNode VisitArtithmeticExpression([NotNull] QLanguageParser.ArtithmeticExpressionContext context)
        {
            if (context.ID() != null)
                return new IdentifierNode(context.ID().GetText());

            if (context.DOUBLE() != null)
                return new LiteralNode(context.DOUBLE().GetText(), QValueType.DOUBLE);

            if (context.INT() != null)
                return new LiteralNode(context.INT().GetText(), QValueType.INTEGER);

            if (context.MULT() != null || context.DIV() != null || context.PLUS() != null || context.MINUS() != null)
            {
                var left = VisitArtithmeticExpression(context.LEFT);
                var opr = GetArthimeticOperator(context);
                var right = VisitArtithmeticExpression(context.RIGHT);
                return new ArthimetricExpressionNode(left, opr, right);
            }

            if (context.artithmeticExpression() != null)
                return VisitArtithmeticExpression(context.artithmeticExpression()[0]);


            return base.VisitArtithmeticExpression(context);
        }


        private ArthimetricOperator GetArthimeticOperator(QLanguageParser.ArtithmeticExpressionContext context)
        {
            if (context.MULT() != null)
                return ArthimetricExpressionNode.ParseArthimeticOperator(context.MULT().GetText());

            if (context.DIV() != null)
                return ArthimetricExpressionNode.ParseArthimeticOperator(context.DIV().GetText());

            if (context.PLUS() != null)
                return ArthimetricExpressionNode.ParseArthimeticOperator(context.PLUS().GetText());

            if (context.MINUS() != null)
                return ArthimetricExpressionNode.ParseArthimeticOperator(context.MINUS().GetText());

            throw new UnknownOperatorException("We don't know what to do here!");
        }
    }
}
