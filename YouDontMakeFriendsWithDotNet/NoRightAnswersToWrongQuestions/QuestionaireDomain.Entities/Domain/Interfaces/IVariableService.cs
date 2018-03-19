using System;

namespace QuestionnaireDomain.Entities.Domain.Interfaces
{
    public interface IVariableService
    {
        Type GetType(string variableName);
        bool AreCompatible(string variableName1, string variableName2);
    }
}