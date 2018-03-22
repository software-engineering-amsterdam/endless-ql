using System;
using QuestionnaireDomain.Entities.Ast.Nodes.Calculation.Interfaces;
using QuestionnaireDomain.Entities.Ast.Nodes.Common.Interfaces;

namespace QuestionnaireDomain.Entities.Domain.Interfaces
{
    public interface IVariableService
    {
        Type GetType(string variableName);
        bool AreCompatible(string variableName1, string variableName2);
        //bool IsVariableCalculation(string variableName, Reference<ICalculationNode> calculation)
        decimal GetNumberValue(string variableName);
    }
}