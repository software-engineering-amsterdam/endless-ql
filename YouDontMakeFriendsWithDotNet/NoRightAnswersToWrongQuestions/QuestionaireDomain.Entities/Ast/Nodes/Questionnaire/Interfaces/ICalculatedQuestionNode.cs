using QuestionaireDomain.Entities.Ast.Nodes.Calculation.Interfaces;
using QuestionaireDomain.Entities.DomainObjects;

namespace QuestionaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces
{
    public interface ICalculatedQuestionNode : IQuestionNode
    {
        Reference<ICalculationNode> CalculatedValue { get; }
    }
}