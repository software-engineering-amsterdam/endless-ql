using QuestionaireDomain.Entities.Ast.Nodes.Boolean.Interfaces;
using QuestionaireDomain.Entities.Ast.Nodes.Common.Interfaces;
using QuestionaireDomain.Entities.DomainObjects;

namespace QuestionaireDomain.Entities.Ast.Nodes.Relational.Interfaces
{
    public interface IRelationalLogicNode : IBooleanLogicNode
    {
        Reference<IAstNode> LeftExpression { get; }
        Reference<IAstNode> RightExpression { get; }
    }
}
