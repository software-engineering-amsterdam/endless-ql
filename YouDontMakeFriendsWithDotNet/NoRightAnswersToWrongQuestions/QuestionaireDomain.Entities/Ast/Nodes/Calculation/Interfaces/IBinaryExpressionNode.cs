using QuestionnaireDomain.Entities.Domain;

namespace QuestionnaireDomain.Entities.Ast.Nodes.Calculation.Interfaces
{
    public interface IBinaryExpressionNode : IExpressionNode
    {
        DomainId<ICalculationNode> LeftCalculation { get; }
        DomainId<ICalculationNode> RightCalculation { get; }
    }
}