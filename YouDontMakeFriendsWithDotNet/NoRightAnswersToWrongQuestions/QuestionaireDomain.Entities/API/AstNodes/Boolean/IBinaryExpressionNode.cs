namespace QuestionaireDomain.Entities.API.AstNodes.Boolean
{
    public interface IBinaryExpressionNode : IExpressionNode
    {
        IBooleanLogicNode LeftExpression { get; set; }
        IBooleanLogicNode RightExpression { get; set; }
    }
}