using QuestionaireDomain.Entities.API;
using QuestionaireDomain.Entities.API.AstNodes.Boolean;
using QuestionaireDomain.Entities.DomainObjects;
using QuestionnaireDomain.Logic.API;

namespace QuestionnaireDomain.Logic.Logic
{
    internal class PredicateCreator : IPredicateCreator
    {
        private readonly IQlInterpretor m_qlInterpretor;

        public PredicateCreator(IQlInterpretor qlInterpretor)
        {
            m_qlInterpretor = qlInterpretor;
        }

        public Reference<IBooleanLogicNode> Create(string definition)
        {
            return m_qlInterpretor.BuildPredicate(definition);
        }
        
    }
}