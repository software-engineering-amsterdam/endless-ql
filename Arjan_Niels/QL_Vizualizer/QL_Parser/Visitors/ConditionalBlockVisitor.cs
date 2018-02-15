using Antlr4.Runtime.Misc;
using QL_Parser.AST.Nodes;
using QLanguage;

namespace QL_Parser.Visitors
{
    public class ConditionalBlockVisitor : QLanguage.QLanguageBaseVisitor<ConditionalNode>
    {
        public override ConditionalNode VisitConditionalBlock([NotNull] QLanguageParser.ConditionalBlockContext context)
        {
            // Create a conditionalBlock wherein we can store the results.
            var conditionalNode = new ConditionalNode();

            // Process the statements and add them to the conditionalBlock.
            //var statementContext = context.statement();
            //var statementVisitor = new StatementVisitor();
            //conditionalBlock.Statement = statementVisitor.VisitStatement(statementContext);

            // Get the sections and process them.
            var sectionContext = context.section();
            var sectionVisitor = new SectionVisitor();
            foreach (var ctx in sectionContext)
                conditionalNode.AddNode(sectionVisitor.VisitSection(ctx));

            return conditionalNode;
        }
    }
}
