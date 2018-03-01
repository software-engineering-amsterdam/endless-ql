using Antlr4.Runtime.Misc;
using QL_Parser.AST.Nodes;
using QL_Parser.AST.Nodes.ExpressionNodes;
using QL_Parser.Exceptions;
using QLanguage;

namespace QL_Parser.Visitors
{
    public class ArthimeticExpressionVisitor : QLanguage.QLanguageBaseVisitor<IExpressionNode>
    {
        public override IExpressionNode VisitArtithmeticExpression([NotNull] QLanguageParser.ArtithmeticExpressionContext context)
        {
            if (context.ID() != null)
                return new IdentifierNode(context.ID().GetText());

            if (context.MULT() != null || context.DIV() != null || context.PLUS() != null || context.MINUS() != null)
            {
                var left = VisitArtithmeticExpression(context.LEFT);
                var opr = GetArthimeticOperator(context);
                var right = VisitArtithmeticExpression(context.RIGHT);
                return new ArthimeticExpressionNode(left, opr, right);
            }

            if (context.artithmeticExpression() != null)
                return VisitArtithmeticExpression(context.artithmeticExpression()[0]);


            return base.VisitArtithmeticExpression(context);
        }


        private ArthimeticOperator GetArthimeticOperator(QLanguageParser.ArtithmeticExpressionContext context)
        {
            if (context.MULT() != null)
                return ArthimeticExpressionNode.ParseArthimeticOperator(context.MULT().GetText());

            if (context.DIV() != null)
                return ArthimeticExpressionNode.ParseArthimeticOperator(context.DIV().GetText());

            if (context.PLUS() != null)
                return ArthimeticExpressionNode.ParseArthimeticOperator(context.PLUS().GetText());

            if (context.MINUS() != null)
                return ArthimeticExpressionNode.ParseArthimeticOperator(context.MINUS().GetText());

            throw new UnknownOperatorException("We don't know what to do here!");
        }
    }
}
