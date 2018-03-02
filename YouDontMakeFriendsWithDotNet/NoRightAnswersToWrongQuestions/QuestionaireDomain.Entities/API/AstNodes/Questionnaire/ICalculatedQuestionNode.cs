using QuestionaireDomain.Entities.API.AstNodes.Calculation;

namespace QuestionaireDomain.Entities.API.AstNodes.Questionnaire
{
    public interface ICalculatedQuestionNode : IQuestionNode
    {
        ICalculationNode CalculatedValue { get; }
    }
}