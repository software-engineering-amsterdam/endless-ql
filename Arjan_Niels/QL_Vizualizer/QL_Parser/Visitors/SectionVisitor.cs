

using Antlr4.Runtime.Misc;
using QL_Parser.Models;
using QLanguage;
using static QLanguage.QLanguageParser;

namespace QL_Parser.Visitors
{
   public class SectionVisitor : QLanguage.QLanguageBaseVisitor<Section>
    {
        public override Section VisitSection([NotNull] QLanguageParser.SectionContext context)
        {
            //Iterate over all the questions
            QuestionContext questionContext = context.question();

            Section section = new Section();
            section.ID = questionContext.GetText();

            return section;
        }
    }
}
