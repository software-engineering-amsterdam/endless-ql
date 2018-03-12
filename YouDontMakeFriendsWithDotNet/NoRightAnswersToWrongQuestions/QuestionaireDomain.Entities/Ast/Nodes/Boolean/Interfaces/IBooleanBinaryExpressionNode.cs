using QuestionnaireDomain.Entities.DomainObjects;

namespace QuestionnaireDomain.Entities.Ast.Nodes.Boolean.Interfaces
{
    public interface IBinaryExpressionNode : IExpressionNode
    {
        Reference<IBooleanLogicNode> LeftExpression { get; }
        Reference<IBooleanLogicNode> RightExpression { get; }
    }
}