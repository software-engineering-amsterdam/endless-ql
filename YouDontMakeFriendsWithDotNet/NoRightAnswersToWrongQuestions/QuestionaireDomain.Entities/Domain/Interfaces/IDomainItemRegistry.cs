using System;
using System.Collections.Generic;
using QuestionnaireDomain.Entities.Output.Nodes.Interfaces;

namespace QuestionnaireDomain.Entities.Domain.Interfaces
{
    public interface IDomainItemRegistry
    {
        void Add(IDomainItem item);
        T Find<T>(Guid id) where T : IDomainItem;
        IEnumerable<T> GetAll<T>() where T : IDomainItem;
        //ToDo: clean the registry better - probably to do with scope!
        void Nuke();
        void Delete<T>(Reference<T> outputItem) where T : IDomainItem;
    }
}