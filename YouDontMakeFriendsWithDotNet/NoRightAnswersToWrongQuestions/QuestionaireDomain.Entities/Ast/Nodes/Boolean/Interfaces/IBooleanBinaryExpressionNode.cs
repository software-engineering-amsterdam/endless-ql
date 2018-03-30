using QuestionnaireDomain.Entities.Domain;

namespace QuestionnaireDomain.Entities.Ast.Nodes.Boolean.Interfaces
{
    public interface IBinaryExpressionNode : IExpressionNode
    {
        DomainId<IBooleanLogicNode> LeftExpression { get; }
        DomainId<IBooleanLogicNode> RightExpression { get; }
    }
}