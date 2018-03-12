using System;
using QuestionaireDomain.Entities.API.AstNodes.Calculation;

namespace QuestionaireDomain.Entities.DomainObjects.Ast
{
    internal abstract class CalculationBinaryExpressionNodeBase : 
        AstNodeBase
    {
        public Reference<ICalculationNode> LeftCalculation { get; }
        public Reference<ICalculationNode> RightCalculation { get; }

        protected CalculationBinaryExpressionNodeBase(
            Guid id, 
            string definition,
            Reference<ICalculationNode> leftCalculation,
            Reference<ICalculationNode> rightCalculation) : base(id, definition)
        {
            LeftCalculation = leftCalculation;
            RightCalculation = rightCalculation;
        }
    }
}