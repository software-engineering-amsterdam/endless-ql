using System.Collections.Generic;
using QuestionnaireDomain.Entities.Ast.Nodes.Common.Interfaces;
using QuestionnaireDomain.Entities.Domain;

namespace QlsTransformer.Ast.Nodes
{
    public interface IPageNode : IAstNode
    {
        string Name { get; }
        IEnumerable<DomainId<ISectionNode>> Sections { get; }
    }
}