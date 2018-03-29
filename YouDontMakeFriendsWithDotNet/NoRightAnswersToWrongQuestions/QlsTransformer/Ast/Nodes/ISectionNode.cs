using QuestionnaireDomain.Entities.Ast.Nodes.Common.Interfaces;

namespace QlsTransformer.Ast.Nodes
{
    public interface ISectionNode : IAstNode
    {
        string Name { get; }
    }
}