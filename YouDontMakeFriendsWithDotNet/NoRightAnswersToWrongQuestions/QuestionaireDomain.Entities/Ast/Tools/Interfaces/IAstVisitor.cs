using QuestionnaireDomain.Entities.Ast.Nodes.Common.Interfaces;

namespace QuestionnaireDomain.Entities.Ast.Tools.Interfaces
{
    public interface IAstVisitor { }
    public interface IAstVisitor<in TNode> where TNode : IAstNode
    {
        void Visit(TNode andNode);
    }
}