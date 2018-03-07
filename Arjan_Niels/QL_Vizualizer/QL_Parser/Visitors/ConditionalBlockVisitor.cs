using Antlr4.Runtime.Misc;
using QL_Parser.AST.Nodes;
using QL_Parser.Visitors.ExpressionVisitors;
using QLanguage;

namespace QL_Parser.Visitors
{
    public class ConditionalBlockVisitor : QLanguage.QLanguageBaseVisitor<ConditionalNode>
    {
        public override ConditionalNode VisitConditionalBlock([NotNull] QLanguageParser.ConditionalBlockContext context)
        {
            // Process the statements and add them to the conditionalBlock.
            var logicalExpressionVisitor = new LogicalExpressionVisitor();
            var expression = logicalExpressionVisitor.VisitLogicalExpression(context.logicalExpression());

            //Create a conditionalBlock wherein we can store the results.
            var conditionalNode = new ConditionalNode(expression);

            // Get the sections and process them.
            var sectionContext = context.section();
            var sectionVisitor = new SectionVisitor();
            foreach (var ctx in sectionContext)
                conditionalNode.AddNode(sectionVisitor.VisitSection(ctx));

            return conditionalNode;
        }
    }
}
