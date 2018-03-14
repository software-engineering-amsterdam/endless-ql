using Antlr4.Runtime.Misc;
using QLGrammar;
using QLParser.AST;
using QLParser.AST.Nodes;
using QLParser.Visitors.ExpressionVisitors;
using static QLGrammar.QLGrammarParser;

namespace QLParser.Visitors
{
    public class ConditionalBlockVisitor : QLGrammarBaseVisitor<ConditionalNode>
    {
        public override ConditionalNode VisitConditionalBlock([NotNull] ConditionalBlockContext context)
        {
            // Process the statements and add them to the conditionalBlock.
            var logicalExpressionVisitor = new LogicalExpressionVisitor();
            var expression = logicalExpressionVisitor.VisitLogicalExpression(context.logicalExpression());

            //Create a conditionalBlock wherein we can store the results.
            var conditionalNode = new ConditionalNode(Location.FromContext(context), expression);

            // Get the sections and process them.
            var sectionContext = context.section();
            var sectionVisitor = new SectionVisitor();
            foreach (var ctx in sectionContext)
                conditionalNode.AddNode(sectionVisitor.VisitSection(ctx));

            return conditionalNode;
        }
    }
}
