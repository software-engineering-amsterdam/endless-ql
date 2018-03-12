using QuestionnaireDomain.Entities.Ast.Nodes.Boolean.Interfaces;
using QuestionnaireDomain.Entities.Ast.Tools.Interfaces;
using QuestionnaireDomain.Entities.Domain;

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