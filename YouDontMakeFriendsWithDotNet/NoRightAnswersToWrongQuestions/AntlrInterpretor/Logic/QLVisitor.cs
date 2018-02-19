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
            var formName = context.FORM_ID().GetText();
            m_questionnaireAst.FormName = formName;
            return m_questionnaireAst;
        }
    }
}