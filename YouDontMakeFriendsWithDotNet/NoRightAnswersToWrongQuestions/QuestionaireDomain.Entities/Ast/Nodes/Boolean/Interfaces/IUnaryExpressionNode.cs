using QuestionaireDomain.Entities.DomainObjects;

namespace QuestionaireDomain.Entities.Ast.Nodes.Boolean.Interfaces
{
    public interface IUnaryExpressionNode : IExpressionNode
    {
        Reference<IBooleanLogicNode> Expression { get; set; }
    }
}