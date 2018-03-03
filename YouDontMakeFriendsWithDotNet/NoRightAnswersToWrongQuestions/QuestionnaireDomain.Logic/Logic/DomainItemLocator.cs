using System;
using System.Collections.Generic;
using QuestionaireDomain.Entities.API;
using QuestionnaireDomain.Logic.API;

namespace QuestionnaireDomain.Logic.Logic
{
    public class DomainItemLocator : IDomainItemLocator
    {
        private readonly IDomainItemRegistry m_registry;

        public DomainItemLocator(IDomainItemRegistry registry)
        {
            m_registry = registry;
        }
        public TDomainItem Get<TDomainItem>(Guid id) where TDomainItem : IDomainItem
        {
            return m_registry.Find<TDomainItem>(id);
        }

        public IEnumerable<TDomainItem> GetAll<TDomainItem>() where TDomainItem : IDomainItem
        {
            return m_registry.GetAll<TDomainItem>();
        }
    }
}