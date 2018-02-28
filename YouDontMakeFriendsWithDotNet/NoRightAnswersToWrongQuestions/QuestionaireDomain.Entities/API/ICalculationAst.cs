using System;
using QuestionaireDomain.Entities.DomainObjects;

namespace QuestionaireDomain.Entities.API
{
    public interface ICalculationAst : IAstNode
    {
        string CalculationDefinition { get; }
        MathOperator Operator { get; set; }
        MathValue Value { get; set; }
        Reference<ICalculationAst> LeftChild { get; set; }
        Reference<ICalculationAst> RightChild { get; set; }
    }


}