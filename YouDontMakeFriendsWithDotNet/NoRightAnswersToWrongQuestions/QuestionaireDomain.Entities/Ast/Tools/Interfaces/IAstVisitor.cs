using QuestionnaireDomain.Entities.Ast.Nodes.Common.Interfaces;

namespace QuestionnaireDomain.Entities.API
{
    public interface IAstVisitor { }
    public interface IAstVisitor<in TNode> where TNode : IAstNode
    {
        void Visit(TNode andNode);
    }
}