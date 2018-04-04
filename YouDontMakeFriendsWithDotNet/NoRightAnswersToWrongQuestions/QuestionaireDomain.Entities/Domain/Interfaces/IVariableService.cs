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