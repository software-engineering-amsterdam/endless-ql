using System;
using QuestionnaireDomain.Entities.Ast.Nodes.Common.Interfaces;
using QuestionnaireDomain.Entities.Domain.Interfaces;

namespace QuestionnaireDomain.Entities.Domain
{
    public class DomainId<T> where T : IDomainItem
    {
        public DomainId(Guid id)
        {
            Id = id;
        }

        public Guid Id { get; }

        public static implicit operator DomainId<IAstNode>(DomainId<T> d)
        {
            return new DomainId<IAstNode>(d.Id);
        }

        public T ToDomainItem(IDomainItemLocator domainItemLocator)
        {
            return domainItemLocator.Get<T>(Id);
        }
    }
}