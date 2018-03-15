using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Output.Tools.Interfaces;

namespace QuestionnaireDomain.Entities.Domain.Interfaces
{
    internal class VariableUpdater : IVariableUpdater
    {
        private readonly ISymbolTable m_symbolTable;
        private readonly IQuestionnaireOutputCreator m_questionnaireOutputCreator;
        private readonly IDomainItemLocator m_domainItemLocator;

        public VariableUpdater(
            ISymbolTable symbolTable,
            IQuestionnaireOutputCreator questionnaireOutputCreator,
            IDomainItemLocator domainItemLocator)
        {
            m_symbolTable = symbolTable;
            m_questionnaireOutputCreator = questionnaireOutputCreator;
            m_domainItemLocator = domainItemLocator;
        }

        public void Update<T>(Reference<IQuestionNode> node, T value)
        {
            m_symbolTable.Update(node.Id, value);
            var root = m_domainItemLocator.GetRoot(node);
            m_questionnaireOutputCreator.Create(root);
        }
    }
}