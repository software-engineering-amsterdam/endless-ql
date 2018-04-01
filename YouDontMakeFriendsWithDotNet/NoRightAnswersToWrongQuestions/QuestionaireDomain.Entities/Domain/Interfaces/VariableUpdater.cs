using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Output.Tools.Interfaces;

namespace QuestionnaireDomain.Entities.Domain.Interfaces
{
    internal class VariableUpdater : IVariableUpdater
    {
        private readonly ISymbolTable m_symbolTable;
        private readonly IQuestionnaireOutputCreator m_questionnaireOutputCreator;
        private readonly IDomainItemLocator m_domainItemLocator;
        private readonly ICalculationService m_calculationService;

        public VariableUpdater(
            ISymbolTable symbolTable,
            IQuestionnaireOutputCreator questionnaireOutputCreator,
            IDomainItemLocator domainItemLocator,
            ICalculationService calculationService)
        {
            m_symbolTable = symbolTable;
            m_questionnaireOutputCreator = questionnaireOutputCreator;
            m_domainItemLocator = domainItemLocator;
            m_calculationService = calculationService;
        }

        public void Update(DomainId<IQuestionNode> node, dynamic value)
        {
            m_symbolTable.Update(node.Id, value);
            m_calculationService.UpdateCalculations();
            var root = m_domainItemLocator.GetRoot(node);
            m_questionnaireOutputCreator.CreateOrUpdate(root);
        }
    }
}