using QuestionaireDomain.Entities.Ast.Nodes.Common.Interfaces;

namespace QuestionaireDomain.Entities.API
{
    public interface IAstVisitor { }
    public interface IAstVisitor<in TNode> where TNode : IAstNode
    {
        void Visit(TNode andNode);
    }
}