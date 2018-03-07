using QuestionaireDomain.Entities.DomainObjects;

namespace QuestionaireDomain.Entities.API.AstNodes.Boolean
{
    public interface IUnaryExpressionNode : IExpressionNode
    {
        Reference<IBooleanLogicNode> Expression { get; set; }
    }
}