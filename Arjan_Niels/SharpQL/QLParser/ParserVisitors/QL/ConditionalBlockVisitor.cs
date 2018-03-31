using QLGrammar;
using QLParser.AST;
using QLParser.AST.QL;
using QLParser.ParserVisitors.QL.ExpressionVisitors;
using System;
using static QLGrammar.QLGrammarParser;

namespace QLParser.ParserVisitors.QL
{
    public class ConditionalBlockVisitor : QLGrammarBaseVisitor<ConditionalNode>
    {
        public override ConditionalNode VisitConditionalBlock(ConditionalBlockContext context)
        {
            if (context == null)
                throw new ArgumentNullException("Context can't be null");

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
