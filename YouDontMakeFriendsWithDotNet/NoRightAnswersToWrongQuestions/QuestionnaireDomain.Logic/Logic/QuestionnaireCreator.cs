using System;
using QuestionaireDomain.Entities.API;
using QuestionnaireDomain.Logic.API;

namespace QuestionnaireDomain.Logic.Logic
{
    internal class QuestionnaireCreator : IQuestionnaireCreator
    {
        private readonly IQlInterpretor m_qlInterpretor;

        public QuestionnaireCreator(IQlInterpretor qlInterpretor)
        {
            m_qlInterpretor = qlInterpretor;
        }

        public IQuestionnaireAst Create(string definition)
        {
            return m_qlInterpretor.BuildForm(definition);
        }
    }
}
