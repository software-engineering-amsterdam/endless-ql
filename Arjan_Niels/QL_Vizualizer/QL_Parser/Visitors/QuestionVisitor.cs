using Antlr4.Runtime.Misc;
using QL_Parser.Models;
using QLanguage;

namespace QL_Parser.Visitors
{
    public class QuestionVisitor : QLanguageBaseVisitor<Question>
    {
        public override Question VisitQuestion([NotNull] QLanguageParser.QuestionContext context)
        {
            return new Question()
            {
                ID = context.ID().GetText(),
                Text = context.TEXT().GetText(),
                QType = context.QTYPE().GetText()
            };
        }
    }
}
