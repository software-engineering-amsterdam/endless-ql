using QuestionnaireDomain.Entities.Ast.Nodes.Calculation.Interfaces;
using QuestionnaireDomain.Entities.Domain;

namespace QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces
{
    public interface ICalculatedQuestionNode : IQuestionNode
    {
        Reference<ICalculationNode> CalculatedValue { get; }
    }
}