using QuestionnaireDomain.Entities.Domain;

namespace QuestionnaireDomain.Entities.Ast.Nodes.Boolean.Interfaces
{
    public interface IUnaryExpressionNode : IExpressionNode
    {
        Reference<IBooleanLogicNode> Expression { get; set; }
    }
}