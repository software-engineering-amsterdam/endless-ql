using System.Collections.Generic;
using QuestionnaireDomain.Entities.API.Output;
using QuestionnaireDomain.Entities.Ast.Nodes.Common.Interfaces;
using QuestionnaireDomain.Entities.DomainObjects;

namespace QuestionnaireDomain.Entities.API
{
    public interface IAstToOutputVisitor { }

    public interface IAstToOutputVisitor<TAst, TOutput>
        where TAst : IAstNode
        where TOutput : IOutputItem
    {
        Reference<TOutput> Visit(Reference<TAst> node);
    }
}