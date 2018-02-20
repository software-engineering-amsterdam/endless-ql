using System.Linq;
using AntlGrammar;
using QuestionaireDomain.Entities.API;
using QuestionaireDomain.Entities.DomainObjects;

namespace AntlrInterpretor.Logic
{
    public class QlVisitor : QLBaseVisitor<IAstNode>
    {
        private readonly IQuestionnaireAst m_questionnaireAst;

        public QlVisitor()
        {
            m_questionnaireAst = new QuestionnaireAst();
        }

        public override IAstNode VisitQuestionnaire(QLParser.QuestionnaireContext context)
        {
            var formName = context.IDENT().GetText();
            var statements = context.question()
                .Select(x => Visit(x))
                .ToList();
            m_questionnaireAst.FormName = formName;
            return m_questionnaireAst;
        }

        public override IAstNode VisitQuestion(QLParser.QuestionContext context)
        {
            var name = context.IDENT().GetText();
            var text = context.STRING().GetText();

            var question = new QuestionAst(name, text.Replace("\"", ""));
            m_questionnaireAst.Questions.Add(question);
            return m_questionnaireAst;
        }
    }
}