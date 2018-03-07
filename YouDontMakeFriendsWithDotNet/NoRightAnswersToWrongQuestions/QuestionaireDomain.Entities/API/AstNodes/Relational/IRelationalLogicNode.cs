using QuestionaireDomain.Entities.API.AstNodes.Boolean;
using QuestionaireDomain.Entities.DomainObjects;

namespace QuestionaireDomain.Entities.API.AstNodes.Relational
{
    public interface IRelationalLogicNode : IBooleanLogicNode
    {
        Reference<IAstNode> LeftExpression { get; }
        Reference<IAstNode> RightExpression { get; }
    }
}
