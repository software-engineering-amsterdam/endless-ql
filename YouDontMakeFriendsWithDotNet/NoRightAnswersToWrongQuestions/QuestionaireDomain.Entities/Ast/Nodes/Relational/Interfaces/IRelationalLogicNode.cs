using QuestionnaireDomain.Entities.Ast.Nodes.Boolean.Interfaces;
using QuestionnaireDomain.Entities.Ast.Nodes.Common.Interfaces;
using QuestionnaireDomain.Entities.Domain;

namespace QuestionnaireDomain.Entities.Ast.Nodes.Relational.Interfaces
{
    public interface IRelationalLogicNode : IBooleanLogicNode
    {
        Reference<IAstNode> LeftExpression { get; }
        Reference<IAstNode> RightExpression { get; }
    }
}
