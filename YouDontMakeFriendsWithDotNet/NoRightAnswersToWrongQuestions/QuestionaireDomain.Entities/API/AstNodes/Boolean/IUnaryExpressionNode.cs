namespace QuestionaireDomain.Entities.API.AstNodes.Boolean
{
    public interface IUnaryExpressionNode : IExpressionNode
    {
        IBooleanLogicNode Expression { get; set; }
    }
}