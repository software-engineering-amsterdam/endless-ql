using QuestionaireDomain.Entities.API.AstNodes;

namespace QuestionaireDomain.Entities.API
{
    public interface IAstVisitor { }
    public interface IAstVisitor<in TNode> where TNode : IAstNode
    {
        void Visit(TNode andNode);
    }
}