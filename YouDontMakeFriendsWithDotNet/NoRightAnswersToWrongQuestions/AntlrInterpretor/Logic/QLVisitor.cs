using AntlGrammar;
using QuestionaireDomain.Entities.API;
using QuestionaireDomain.Entities.DomainObjects;

namespace AntlrInterpretor.Logic
{
    public class QlVisitor : QLBaseVisitor<IQuestionnaireAst>
    {
        private readonly IQuestionnaireAst m_questionnaireAst;

        public QlVisitor()
        {
            m_questionnaireAst = new QuestionnaireAst();
        }

        public override IQuestionnaireAst VisitQuestionnaire(QLParser.QuestionnaireContext context)
        {
            var formName = context.IDENT().GetText();
            m_questionnaireAst.FormName = formName;
            return m_questionnaireAst;
        }

        public override IQuestionnaireAst VisitQuestion(QLParser.QuestionContext context)
        {

            var name = context.IDENT().GetText();
            var text = context.STRING().GetText();

            var question = new QuestionAst(name, text);
            m_questionnaireAst.Questions.Add(question);
            return m_questionnaireAst;
        }
    }
}