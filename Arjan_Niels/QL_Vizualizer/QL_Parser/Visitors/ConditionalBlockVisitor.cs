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
            var conditionalBlock = new ConditionalBlock();

            // Get the sections
            SectionContext[] sectionContext = context.section();
            SectionVisitor visitor = new SectionVisitor();
            foreach (SectionContext ctx in sectionContext)
                conditionalBlock.Sections.Add(visitor.VisitSection(ctx));

            return conditionalBlock;
        }
    }
}
