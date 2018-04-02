using QuestionnaireDomain.Entities.Ast.Nodes.Common.Interfaces;
using QuestionnaireDomain.Entities.Domain;
using QuestionnaireDomain.Entities.Output.Nodes.Interfaces;

namespace QuestionnaireDomain.Entities.Output.Tools.Interfaces
{
    public interface IAstToOutputVisitor
    {
    }

    public interface IAstToOutputVisitor<TAst, TOutput>
        where TAst : IAstNode
        where TOutput : IOutputItem
    {
        DomainId<TOutput> Visit(DomainId<TAst> node);
    }
}