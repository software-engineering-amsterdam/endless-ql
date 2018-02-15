using Antlr4.Runtime.Misc;
using QL_Parser.Models;
using QLanguage;
using static QLanguage.QLanguageParser;

namespace QL_Parser.Visitors
{
    public class ConditionalBlockVisitor : QLanguage.QLanguageBaseVisitor<ConditionalBlock>
    {
        public override ConditionalBlock VisitConditionalBlock([NotNull] QLanguageParser.ConditionalBlockContext context)
        {
            // Create a conditionalBlock wherein we can store the results.
            var conditionalBlock = new ConditionalBlock();

            // Process the statements and add them to the conditionalBlock.
            var statementContext = context.statement();
            var statementVisitor = new StatementVisitor();
            conditionalBlock.Statement = statementVisitor.VisitStatement(statementContext);

            // Get the sections and process them.
            var sectionContext = context.section();
            var sectionVisitor = new SectionVisitor();
            foreach (var ctx in sectionContext)
                conditionalBlock.Sections.Add(sectionVisitor.VisitSection(ctx));

            return conditionalBlock;
        }
    }
}
