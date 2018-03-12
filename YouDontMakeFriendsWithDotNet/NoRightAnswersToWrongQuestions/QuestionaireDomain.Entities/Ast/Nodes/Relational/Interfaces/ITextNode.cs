using QuestionaireDomain.Entities.Ast.Nodes.Common.Interfaces;

namespace QuestionaireDomain.Entities.Ast.Nodes.Relational.Interfaces
{
    public interface ITextNode : IAstNode
    {
        string Value { get; }
    }
}