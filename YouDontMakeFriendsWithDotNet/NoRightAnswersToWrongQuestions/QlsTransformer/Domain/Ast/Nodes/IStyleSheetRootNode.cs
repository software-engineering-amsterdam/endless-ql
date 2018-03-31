using QuestionnaireDomain.Entities.Ast.Nodes.Common.Interfaces;

namespace QlsTransformer.Ast.Nodes
{
    public interface IStyleSheetRootNode : IAstNode
    {
        string Name { get; }
    }
}