using AntlGrammar;
using QuestionaireDomain.Entities.API;
using QuestionaireDomain.Entities.DomainObjects;

namespace AntlrInterpretor.Logic
{
    public class QLVisitor : QLBaseVisitor<IQuestionnaire>
    {
        private readonly IQuestionnaire m_questionnaire;

        public QLVisitor()
        {
            m_questionnaire = new Questionnaire();    
        }
        public override IQuestionnaire VisitQuestionnaire(QLParser.QuestionnaireContext context)
        {
            var formName = context.FORM_ID().GetText();
            m_questionnaire.FormName = formName;
            return m_questionnaire;
        }
    }
}