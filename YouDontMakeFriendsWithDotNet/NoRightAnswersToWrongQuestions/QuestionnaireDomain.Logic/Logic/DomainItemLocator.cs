using System;
using QuestionaireDomain.Entities.API;
using QuestionnaireDomain.Logic.API;

namespace QuestionnaireDomain.Logic.Logic
{
    public class DomainItemLocator : IDomainItemLocator
    {
        private readonly DomainItemRegistry m_registry;

        public DomainItemLocator(DomainItemRegistry registry)
        {
            m_registry = registry;
        }
        public TDomainItem Get<TDomainItem>(Guid id) where TDomainItem : IDomainItem
        {
            return m_registry.Find<TDomainItem>(id);
        }
    }
}