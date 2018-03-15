using System;
using System.Collections.Generic;
using System.Linq;
using QuestionnaireDomain.Entities.Domain.Interfaces;
using QuestionnaireDomain.Entities.Output.Nodes.Interfaces;

namespace QuestionnaireDomain.Entities.Domain
{
    public class DomainItemRegistry : IDomainItemRegistry
    {
        private static readonly Dictionary<Guid, IDomainItem> Registry;

        static DomainItemRegistry()
        {
            Registry = new Dictionary<Guid, IDomainItem>();
        }

        public void Add(IDomainItem item)
        {
            Registry.Add(item.Id, item);
        }

        public T Find<T>(Guid id) where T : IDomainItem
        {
            return (T)Registry[id];
        }

        public IEnumerable<T> GetAll<T>() where T : IDomainItem
        {
            return Registry
                .Values
                .OfType<T>()
                .ToList();
        }

        public void Nuke()
        {
            Registry.Clear();
        }

        public void Delete<T>(Reference<T> domainItem) where T : IDomainItem
        {
            if (Registry.ContainsKey(domainItem.Id))
            {
                Registry.Remove(domainItem.Id);
            }
        }
    }
}