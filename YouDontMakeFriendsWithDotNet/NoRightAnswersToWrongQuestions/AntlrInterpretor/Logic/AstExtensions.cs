using System.Collections.Generic;
using System.Linq;
using System.Runtime.Remoting.Channels;
using QuestionaireDomain.Entities.API;
using QuestionaireDomain.Entities.API.AstNodes;
using QuestionaireDomain.Entities.DomainObjects;

namespace AntlrInterpretor.Logic
{
    public static class AstExtensions
    {
        public static T FromLocator<T>(
            this Reference<T> astReference, 
            IDomainItemLocator domainItemLocator) where T : IAstNode
        {
            return domainItemLocator.Get<T>(astReference.Id);
        }

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