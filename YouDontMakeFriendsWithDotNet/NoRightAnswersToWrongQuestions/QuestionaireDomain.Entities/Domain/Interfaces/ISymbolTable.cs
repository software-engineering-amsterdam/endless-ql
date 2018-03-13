using System;

namespace QuestionnaireDomain.Entities.Domain.Interfaces
{
    public interface ISymbolTable<T> 
    {
        void Add(Guid variableRef, T value);
        void Update(Guid variableRef, T value);
        T Lookup(Guid id);
    }
}