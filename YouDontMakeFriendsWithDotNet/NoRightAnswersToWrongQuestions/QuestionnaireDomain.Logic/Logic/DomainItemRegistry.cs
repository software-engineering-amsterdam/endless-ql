using System;
using System.Collections.Generic;
using QuestionaireDomain.Entities.API;

namespace QuestionnaireDomain.Logic.Logic
{
    public class DomainItemRegistry 
    {
        private static readonly Dictionary<Guid, WeakReference> Registry;

        static DomainItemRegistry()
        {
            Registry = new Dictionary<Guid, WeakReference>();
        }

        public void Add(IDomainItem item)
        {
            Registry.Add(item.Id, new WeakReference(item,false));
        }

        public T Find<T>(Guid id) where T : IDomainItem
        {
            return (T)Registry[id].Target;
        }
    }
}