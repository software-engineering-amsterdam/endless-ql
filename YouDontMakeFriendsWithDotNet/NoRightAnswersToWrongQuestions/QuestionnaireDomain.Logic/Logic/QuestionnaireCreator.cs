using System;
using QuestionaireDomain.Entities.API;
using QuestionnaireDomain.Logic.API;

namespace QuestionnaireDomain.Logic.Logic
{
    internal class QuestionnaireCreator : IQuestionnaireCreator
    {
        private readonly IQlInterpretor m_qlInterpretor;
        private readonly DomainItemRegistry m_domainItemRegistry;

        public QuestionnaireCreator(
            IQlInterpretor qlInterpretor,
            DomainItemRegistry domainItemRegistry)
        {
            m_qlInterpretor = qlInterpretor;
            m_domainItemRegistry = domainItemRegistry;
        }

        public Guid Create(string definition)
        {
            var form =  m_qlInterpretor.BuildForm(definition);
            m_domainItemRegistry.Add(form);
            return form.Id;
        }
    }
}
