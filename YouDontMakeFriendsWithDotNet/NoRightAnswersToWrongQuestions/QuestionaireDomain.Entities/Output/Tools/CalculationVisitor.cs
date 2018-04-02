using QuestionnaireDomain.Entities.Ast.Nodes.Calculation.Interfaces;
using QuestionnaireDomain.Entities.Domain;
using QuestionnaireDomain.Entities.Domain.Interfaces;
using QuestionnaireDomain.Entities.Output.Tools.Interfaces;

namespace QuestionnaireDomain.Entities.Output.Tools
{
    internal class CalculationVisitor : ICalculationVisitor
    {
        private readonly IDomainItemLocator m_domainItemLocator;
        private readonly IVariableService m_variableService;

        public CalculationVisitor(
            IDomainItemLocator domainItemLocator,
            IVariableService variableService)
        {
            m_domainItemLocator = domainItemLocator;
            m_variableService = variableService;
        }

        public decimal Calculate(
            DomainId<ICalculationNode> calculationNode)
        {
            var node = m_domainItemLocator.Get<ICalculationNode>(calculationNode.Id);
            dynamic d = node;
            return this.Evaluate(d);
        }

        private decimal Evaluate(INumberNode node)
        {
            return node.Value;
        }

        private decimal Evaluate(ICalculationVariableNode node)
        {
            return m_variableService.GetNumberValue(node.VariableName);
        }

        private decimal Evaluate(IMultiplyNode node)
        {
            return EvaluateLeft(node) * EvaluateRight(node);
        }

        private decimal Evaluate(IDivideNode node)
        {
            return EvaluateLeft(node) / EvaluateRight(node);
        }

        private decimal Evaluate(IAddNode node)
        {
            return EvaluateLeft(node) + EvaluateRight(node);
        }

        private decimal Evaluate(ISubtractNode node)
        {
            return EvaluateLeft(node) - EvaluateRight(node);
        }

        private dynamic EvaluateRight(IBinaryExpressionNode node)
        {
            var rightCalculation = node.RightCalculation.ToDomainItem(m_domainItemLocator);
            dynamic dr = rightCalculation;
            return Evaluate(dr);
        }

        private dynamic EvaluateLeft(IBinaryExpressionNode node)
        {
            var leftCalculation = node.LeftCalculation.ToDomainItem(m_domainItemLocator);
            dynamic dl = leftCalculation;
            return Evaluate(dl);
        }
    }
}