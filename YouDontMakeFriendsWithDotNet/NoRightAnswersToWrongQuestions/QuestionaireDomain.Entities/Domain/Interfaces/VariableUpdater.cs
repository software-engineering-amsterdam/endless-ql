using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Output.Tools.Interfaces;

namespace QuestionnaireDomain.Entities.Domain.Interfaces
{
    internal class VariableUpdater : IVariableUpdater
    {
        private readonly ISymbolTable m_symbolTable;
        private readonly IQuestionnaireModelCreator m_questionnaireModelCreator;
        private readonly IDomainItemLocator m_domainItemLocator;

        public VariableUpdater(
            ISymbolTable symbolTable,
            IQuestionnaireModelCreator questionnaireModelCreator,
            IDomainItemLocator domainItemLocator)
        {
            m_symbolTable = symbolTable;
            m_questionnaireModelCreator = questionnaireModelCreator;
            m_domainItemLocator = domainItemLocator;
        }

        public void Update<T>(Reference<IQuestionNode> node, T value)
        {
            m_symbolTable.Update(node.Id, value);
            var root = m_domainItemLocator.GetRoot(node);
            m_questionnaireModelCreator.Create(root);
        }
    }
}