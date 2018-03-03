using System;
using System.Collections.Generic;

namespace QuestionaireDomain.Entities.API
{
    public interface IDomainItemLocator
    {
        TDomainItem Get<TDomainItem>(Guid id) where TDomainItem : IDomainItem;
        IEnumerable<TDomainItem> GetAll<TDomainItem>() where TDomainItem : IDomainItem;
    }
}