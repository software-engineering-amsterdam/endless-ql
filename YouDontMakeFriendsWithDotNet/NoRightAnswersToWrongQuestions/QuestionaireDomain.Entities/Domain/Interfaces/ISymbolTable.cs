using System;

namespace QuestionnaireDomain.Entities.Domain.Interfaces
{
    public interface ISymbolTable
    {
        Type GetType(Guid variableRef);
        bool Exists(Guid variableRef);
        void Add<T>(Guid variableRef, T value);
        void Update<T>(Guid variableRef, T value);
        bool Exists<T>(Guid variableRef);
        T Lookup<T>(Guid id);
        object Lookup(Guid id);
    }
    
}