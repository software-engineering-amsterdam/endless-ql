using System.Collections.Generic;
using QuestionnaireDomain.Entities.Ast.Nodes.Calculation;
using QuestionnaireDomain.Entities.Ast.Nodes.Calculation.Interfaces;
using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Domain;
using QuestionnaireDomain.Entities.Domain.Interfaces;
using QuestionnaireDomain.Entities.Output.Tools.Interfaces;

namespace QuestionnaireDomain.Entities.Output.Tools
{
    internal class ExtractVariableVisitor : IExtractVariableVisitor
    {
        private readonly IDomainItemLocator m_domainItemLocator;
        private readonly IVariableService m_variableService;
        private readonly IList<IQuestionNode> m_variableList
            = new List<IQuestionNode>();

        public ExtractVariableVisitor(
            IDomainItemLocator domainItemLocator,
            IVariableService variableService)
        {
            m_domainItemLocator = domainItemLocator;
            m_variableService = variableService;
        }

        public IEnumerable<IQuestionNode> Extract(
            DomainId<ICalculationNode> calculationNode)
        {
            m_variableList.Clear();
            var node = m_domainItemLocator.Get<ICalculationNode>(calculationNode.Id);
            dynamic d = node;
            this.Evaluate(d);
            return m_variableList;
        }

        private void Evaluate(ICalculationNode node)
        {
            foreach (var child in node.Children)
            {
                var childDomainItem = child.ToDomainItem(m_domainItemLocator);
                dynamic d = childDomainItem;
                Evaluate(d);
            }
        }

        private void Evaluate(ICalculationVariableNode node)
        {
            var questionNode = m_variableService.GetQuestionNode(node.VariableName);
            if (!m_variableList.Contains(questionNode))
            {
                m_variableList.Add(questionNode);
            }
        }
    }
}