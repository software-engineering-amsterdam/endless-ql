using QuestionaireDomain.Entities.Ast.Nodes.Calculation.Interfaces;
using QuestionaireDomain.Entities.DomainObjects;

namespace QuestionnaireDomain.Logic.Logic
{
    public interface ICalculationVisitor
    {
        decimal Calculate(Reference<ICalculationNode> node);
    }
}