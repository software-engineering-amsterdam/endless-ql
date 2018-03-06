using QuestionaireDomain.Entities.API.AstNodes.Calculation;
using QuestionaireDomain.Entities.DomainObjects;

namespace QuestionaireDomain.Entities.API.AstNodes.Questionnaire
{
    public interface ICalculatedQuestionNode : IQuestionNode
    {
        Reference<ICalculationNode> CalculatedValue { get; }
    }
}