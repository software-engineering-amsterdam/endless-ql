using QuestionaireDomain.Entities.DomainObjects;

namespace QuestionaireDomain.Entities.Ast.Nodes.Boolean.Interfaces
{
    public interface IBinaryExpressionNode : IExpressionNode
    {
        Reference<IBooleanLogicNode> LeftExpression { get; }
        Reference<IBooleanLogicNode> RightExpression { get; }
    }
}