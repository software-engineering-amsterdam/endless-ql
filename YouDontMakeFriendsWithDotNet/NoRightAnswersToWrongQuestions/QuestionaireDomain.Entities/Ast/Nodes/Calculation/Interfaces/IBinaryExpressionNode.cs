using QuestionnaireDomain.Entities.Domain;

namespace QuestionnaireDomain.Entities.Ast.Nodes.Calculation.Interfaces
{
    public interface IBinaryExpressionNode : IExpressionNode
    {
        Reference<ICalculationNode> LeftCalculation { get; }
        Reference<ICalculationNode> RightCalculation { get; }
    }
}