using Antlr4.Runtime.Misc;
using QL_Parser.AST.Nodes;
using System;
using static QLanguage.QLanguageParser;

namespace QL_Parser.Visitors
{
    public class SectionVisitor : QLanguage.QLanguageBaseVisitor<Node>
    {
        public override Node VisitSection([NotNull] SectionContext context)
        {
            // If the Section is a Question, process it.
            var questionContext = context.question();
            if (questionContext != null)
            {
                var questionVisitor = new QuestionVisitor();
                return questionVisitor.VisitQuestion(questionContext);
            }

            // If it isn't a Question it is most likely to be a ConditionalBlock.
            var conditionalContext = context.conditionalBlock();
            if (conditionalContext != null)
            {
                var conditionalBlockVisitor = new ConditionalBlockVisitor();
                return conditionalBlockVisitor.VisitConditionalBlock(conditionalContext);
            }

            // A section can only be a Question or a ConditionalBlock. If it manages to reach this line; throw an
            // exception, because it should not be possible.
            throw new InvalidOperationException("We don't know how to process this section.");
        }
    }
}