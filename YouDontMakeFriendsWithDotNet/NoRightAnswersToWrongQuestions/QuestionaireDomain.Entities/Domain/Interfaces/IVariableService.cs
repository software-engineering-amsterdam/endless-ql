using System;
using QuestionnaireDomain.Entities.Ast.Nodes.Calculation.Interfaces;
using QuestionnaireDomain.Entities.Ast.Nodes.Common.Interfaces;
using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;

namespace QuestionnaireDomain.Entities.Domain.Interfaces
{
    public interface IVariableService
    {
        IQuestionType GetQuestionType(string variableName);
        bool AreCompatible(string variableName1, string variableName2);
        decimal GetNumberValue(string variableName);
        IQuestionNode GetQuestionNode(string variableName);
    }
}