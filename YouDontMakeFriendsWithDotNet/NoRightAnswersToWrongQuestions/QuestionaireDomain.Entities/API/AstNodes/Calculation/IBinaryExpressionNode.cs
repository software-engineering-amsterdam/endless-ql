using QuestionaireDomain.Entities.DomainObjects;

namespace QuestionaireDomain.Entities.API.AstNodes.Calculation
{
    public interface IBinaryExpressionNode : IExpressionNode
    {
        Reference<ICalculationNode> LeftCalculation { get; }
        Reference<ICalculationNode> RightCalculation { get; }
    }
}