using System.Collections.Generic;
using System.Linq;
using QuestionnaireDomain.Entities.Ast.Nodes.Common.Interfaces;
using QuestionnaireDomain.Entities.Domain.Interfaces;

namespace QuestionnaireDomain.Entities.Domain
{
    public static class AstExtensions
    {
        public static IEnumerable<DomainId<T>> To<T>(
            this IEnumerable<DomainId<IAstNode>> nodes,
            IDomainItemLocator domainItemLocator) where T : IAstNode
        {
            return nodes.Select(x => To<T>(x, domainItemLocator)).ToList(); 
        }

        public static DomainId<T> To<T>(
            this DomainId<IAstNode> node, 
            IDomainItemLocator domainItemLocator) where T : IAstNode
        {
            var domainItem = domainItemLocator.Get<T>(node.Id);
            return new DomainId<T>(domainItem.Id);
        }
    }
}