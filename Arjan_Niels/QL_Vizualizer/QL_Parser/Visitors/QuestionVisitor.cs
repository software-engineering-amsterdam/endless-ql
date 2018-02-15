using Antlr4.Runtime.Misc;
using QL_Parser.AST.Nodes;
using QLanguage;

namespace QL_Parser.Visitors
{
    public class QuestionVisitor : QLanguageBaseVisitor<QuestionNode>
    {
        public override QuestionNode VisitQuestion([NotNull] QLanguageParser.QuestionContext context)
        {
            var id = context.ID().GetText();
            var question = context.TEXT().GetText();

            return new QuestionNode(id, question, QuestionType.BOOLEAN);
        }
    }
}
