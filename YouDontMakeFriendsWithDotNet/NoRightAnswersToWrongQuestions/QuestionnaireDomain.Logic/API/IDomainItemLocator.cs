using System;
using QuestionaireDomain.Entities.API;

namespace QuestionnaireDomain.Logic.API
{
    public interface IDomainItemLocator
    {
        TDomainItem Get<TDomainItem>(Guid id) where TDomainItem : IDomainItem;
    }
}