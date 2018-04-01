using System;
using QuestionnaireDomain.Entities.Domain;

namespace QlsTransformer.Domain.Ast.Nodes
{
    public interface IDefaultStyle 
    {
        Type Type { get; }
        DomainId<IStyleNode> Style { get; }
    }
}