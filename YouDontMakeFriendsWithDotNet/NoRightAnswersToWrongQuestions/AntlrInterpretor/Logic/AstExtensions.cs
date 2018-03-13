using System.Collections.Generic;
using System.Linq;
using QuestionnaireDomain.Entities.Ast.Nodes.Common.Interfaces;
using QuestionnaireDomain.Entities.Domain;
using QuestionnaireDomain.Entities.Domain.Interfaces;

namespace AntlrInterpretor.Logic
{
    public static class AstExtensions
    {
        public static IEnumerable<Reference<T>> To<T>(
            this IEnumerable<Reference<IAstNode>> nodes,
            IDomainItemLocator domainItemLocator) where T : IAstNode
        {
            return nodes.Select(x => To<T>(x, domainItemLocator)).ToList(); 
        }

        public static Reference<T> To<T>(
            this Reference<IAstNode> node, 
            IDomainItemLocator domainItemLocator) where T : IAstNode
        {
            var domainItem = domainItemLocator.Get<T>(node.Id);
            return new Reference<T>(domainItem.Id);
        }
    }
}