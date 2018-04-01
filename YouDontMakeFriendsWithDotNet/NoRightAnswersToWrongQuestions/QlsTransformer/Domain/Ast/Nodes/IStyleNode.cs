using QuestionnaireDomain.Entities.Ast.Nodes.Common.Interfaces;

namespace QlsTransformer.Domain.Ast.Nodes
{
    public interface IStyleNode : IAstNode
    {
        IWidget Widget { get; }
        int? Width { get; }
        string Font { get; }
        decimal? FontSize { get; }
        string Color { get; }
    }
}