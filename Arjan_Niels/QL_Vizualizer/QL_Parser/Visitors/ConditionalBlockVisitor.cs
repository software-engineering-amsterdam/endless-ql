using Antlr4.Runtime.Misc;
using QL_Parser.AST.Nodes;
using QLanguage;

namespace QL_Parser.Visitors
{
    public class ConditionalBlockVisitor : QLanguage.QLanguageBaseVisitor<ConditionalNode>
    {
        public override ConditionalNode VisitConditionalBlock([NotNull] QLanguageParser.ConditionalBlockContext context)
        {
            // Process the statements and add them to the conditionalBlock.
            var statementContext = context.statement();
            var statementVisitor = new StatementVisitor();
            var statement = statementVisitor.VisitStatement(statementContext);

            // Create a conditionalBlock wherein we can store the results.
            var conditionalNode = new ConditionalNode(statement);

            // Get the sections and process them.
            var sectionContext = context.section();
            var sectionVisitor = new SectionVisitor();
            foreach (var ctx in sectionContext)
                conditionalNode.AddNode(sectionVisitor.VisitSection(ctx));

            return conditionalNode;
        }
    }
}
