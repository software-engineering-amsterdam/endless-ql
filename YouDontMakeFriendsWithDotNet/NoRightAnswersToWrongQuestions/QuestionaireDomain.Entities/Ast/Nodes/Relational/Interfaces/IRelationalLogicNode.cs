using QuestionnaireDomain.Entities.Ast.Nodes.Boolean.Interfaces;
using QuestionnaireDomain.Entities.Ast.Nodes.Common.Interfaces;
using QuestionnaireDomain.Entities.Domain;

namespace QuestionnaireDomain.Entities.Ast.Nodes.Relational.Interfaces
{
    public interface IRelationalLogicNode : IBooleanLogicNode
    {
        DomainId<IAstNode> LeftExpression { get; }
        DomainId<IAstNode> RightExpression { get; }
    }
}