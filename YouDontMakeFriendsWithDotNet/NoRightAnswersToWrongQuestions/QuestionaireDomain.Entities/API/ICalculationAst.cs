using QuestionaireDomain.Entities.DomainObjects;

namespace QuestionaireDomain.Entities.API
{
    public interface ICalculationAst : IAstNode
    {
        string CalculationName { get; }
    }
}