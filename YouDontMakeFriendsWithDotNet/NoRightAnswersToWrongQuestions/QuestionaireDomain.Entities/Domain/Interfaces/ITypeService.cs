using System;
using QuestionnaireDomain.Entities.Ast.Nodes.Relational.Interfaces;

namespace QuestionnaireDomain.Entities.Domain.Interfaces
{
    public interface ITypeService
    {
        bool IsValidOperationForType(IRelationalLogicNode operation, Type type);
        string GetTypeDisplay(Type type);
    }
}