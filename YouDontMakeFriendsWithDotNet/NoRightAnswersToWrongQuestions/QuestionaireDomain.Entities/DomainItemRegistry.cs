using System;
using System.Collections.Generic;
using System.Linq;
using QuestionaireDomain.Entities.API;

namespace QuestionaireDomain.Entities
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
            return Registry.Values.OfType<T>();
        }

        public void Nuke()
        {
            Registry.Clear();
        }
    }
}