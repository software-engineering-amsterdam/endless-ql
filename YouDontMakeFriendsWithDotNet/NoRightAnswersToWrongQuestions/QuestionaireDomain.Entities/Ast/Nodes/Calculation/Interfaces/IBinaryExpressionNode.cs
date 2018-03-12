using QuestionaireDomain.Entities.DomainObjects;

namespace QuestionaireDomain.Entities.Ast.Nodes.Calculation.Interfaces
{
    public interface IBinaryExpressionNode : IExpressionNode
    {
        Reference<ICalculationNode> LeftCalculation { get; }
        Reference<ICalculationNode> RightCalculation { get; }
    }
}