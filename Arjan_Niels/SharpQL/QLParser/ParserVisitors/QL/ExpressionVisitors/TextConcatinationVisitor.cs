using QLGrammar;
using QLParser.AST;
using QLParser.AST.QL;
using QLParser.AST.QL.ExpressionNodes;
using System;

namespace QLParser.ParserVisitors.QL.ExpressionVisitors
{
    public class TextConcatinationVisitor : QLGrammar.QLGrammarBaseVisitor<IExpressionNode>
    {
        public override IExpressionNode VisitTextConcatination(QLGrammarParser.TextConcatinationContext context)
        {
            if (context.TERMINAL != null)
                return GetTerminal(context);

            IExpressionNode left = GetTerminal(context);
            IExpressionNode right = VisitTextConcatination(context.textConcatination());

            return new TextConcatinationNode(Location.FromContext(context), left, right, NodeType.TextConcatination);
        }

        private IExpressionNode GetTerminal(QLGrammarParser.TextConcatinationContext context)
        {
            Location location = Location.FromContext(context);
            if (context.ID() != null)
                return new IdentifierNode(location, context.ID().GetText());
            else if (context.TEXT() != null)
                return new LiteralNode(location, Util.RemoveQuotes(context.TEXT().GetText()), QValueType.Text);

            throw new NotImplementedException("We shouldn't be able to reach this line of code! Everything should be handled by the statements above");
        }
    }
}
