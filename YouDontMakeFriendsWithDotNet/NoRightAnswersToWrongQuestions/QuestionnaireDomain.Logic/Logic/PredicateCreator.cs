using QuestionaireDomain.Entities.API;
using QuestionaireDomain.Entities.Ast.Nodes.Boolean.Interfaces;
using QuestionaireDomain.Entities.DomainObjects;
using QuestionnaireDomain.Logic.API;

namespace QuestionnaireDomain.Logic.Logic
{
    internal class PredicateCreator : IPredicateCreator
    {
        private readonly IAstTreeBuilder m_astTreeBuilder;

        public PredicateCreator(IAstTreeBuilder astTreeBuilder)
        {
            m_astTreeBuilder = astTreeBuilder;
        }

        public Reference<IBooleanLogicNode> Create(string definition)
        {
            return m_astTreeBuilder.BuildPredicate(definition);
        }
        
    }
}