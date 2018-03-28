using QuestionnaireDomain.Entities.Ast.Nodes.Common.Interfaces;
using QuestionnaireDomain.Entities.Domain;
using QuestionnaireDomain.Entities.Domain.Interfaces;

namespace QlsParser.AstBuilder
{
    public static class AstExtensions
    {
        public static Reference<T> To<T>(
            this Reference<IAstNode> node,
            IDomainItemLocator domainItemLocator) where T : IAstNode
        {
            var domainItem = domainItemLocator.Get<T>(node.Id);
            return new Reference<T>(domainItem.Id);
        }
    }
}