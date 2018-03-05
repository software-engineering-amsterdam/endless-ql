using Antlr4.Runtime.Misc;
using QL_Parser.AST.Nodes;
using QLanguage;

namespace QL_Parser.Visitors
{
    public class StatementVisitor : QLanguageBaseVisitor<IExpressionNode>
    {
        public override IExpressionNode VisitStatement([NotNull] QLanguageParser.StatementContext context)
        {
            var valueVisitor = new ValueVisitor();
            var lhs = valueVisitor.VisitValue(context.value());


            if (context.binary() != null && context.statement() != null)
            {
                var opr = context.binary().GetText();
                var rhs = VisitStatement(context.statement());

                return new StatementNode(lhs, opr, rhs);
            }
            else
            {
                return lhs;
            }
        }
    }
}
