using Antlr4.Runtime.Misc;
using QLGrammar;
using QLParser.AST;
using QLParser.AST.Nodes;
using QLParser.AST.Nodes.ExpressionNodes;
using QLParser.AST.Nodes.ExpressionNodes.Enums;
using QLParser.Exceptions;
using static QLGrammar.QLGrammarParser;

namespace QLParser.Visitors.ExpressionVisitors
{
    public class ArthimetricExpressionVisitor : QLGrammarBaseVisitor<IExpressionNode>
    {
        public override IExpressionNode VisitArtithmeticExpression([NotNull] ArtithmeticExpressionContext context)
        {
            if (context.ID() != null)
                return new IdentifierNode(Location.FromContext(context), context.ID().GetText());

            if (context.DOUBLE() != null)
                return new LiteralNode(Location.FromContext(context), context.DOUBLE().GetText(), QValueType.DOUBLE);

            if (context.INT() != null)
                return new LiteralNode(Location.FromContext(context), context.INT().GetText(), QValueType.INTEGER);

            if (context.MULT() != null || context.DIV() != null || context.PLUS() != null || context.MINUS() != null)
            {
                var left = VisitArtithmeticExpression(context.LEFT);
                var opr = GetArthimeticOperator(context);
                var right = VisitArtithmeticExpression(context.RIGHT);
                return new ArthimetricExpressionNode(Location.FromContext(context), left, opr, right);
            }

            if (context.artithmeticExpression() != null)
                return VisitArtithmeticExpression(context.artithmeticExpression()[0]);


            return base.VisitArtithmeticExpression(context);
        }


        private ArthimetricOperator GetArthimeticOperator(ArtithmeticExpressionContext context)
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
