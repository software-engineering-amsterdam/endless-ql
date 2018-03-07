using System;
using QuestionaireDomain.Entities.API;
using QuestionaireDomain.Entities.API.AstNodes;

namespace QuestionaireDomain.Entities.DomainObjects
{
    public class Reference<T> where T : IAstNode
    {
        public Reference(Guid id)
        {
            Id = id;
        }

        public Guid Id { get; }

        public static implicit operator Reference<IAstNode>(Reference<T> d)
        {
            return new Reference<IAstNode>(d.Id);
        }

        public T ToDomainItem(IDomainItemLocator domainItemLocator)
        {
            return domainItemLocator.Get<T>(Id);
        }
    }
}