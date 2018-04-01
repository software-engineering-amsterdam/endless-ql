using System;
using QuestionnaireDomain.Entities.Domain;

namespace QlsTransformer.Domain.Ast.Nodes
{
    public class DefaultStyle : IDefaultStyle
    {
        public DefaultStyle(Type type, DomainId<IStyleNode> style)
        {
            Type = type;
            Style = style;
        }

        public Type Type { get; }
        public DomainId<IStyleNode> Style { get; }
    }
}