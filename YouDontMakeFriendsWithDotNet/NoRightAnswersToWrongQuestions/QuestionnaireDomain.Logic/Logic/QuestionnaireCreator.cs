using System;
using QuestionaireDomain.Entities.API;
using QuestionaireDomain.Entities.API.AstNodes.Questionnaire;
using QuestionaireDomain.Entities.API.AstNodes.Relational;
using QuestionaireDomain.Entities.DomainObjects;
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

        public Reference<IRootNode> Create(string definition)
        {
            return m_qlInterpretor.BuildForm(definition);
        }
    }
}