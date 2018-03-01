namespace QuestionaireDomain.Entities.API.AstNodes.Calculation
{
    public interface IBinaryExpressionNode : IExpressionNode
    {
        ICalculationNode LeftCalculation { get; set; }
        ICalculationNode RightCalculation { get; set; }
    }
}