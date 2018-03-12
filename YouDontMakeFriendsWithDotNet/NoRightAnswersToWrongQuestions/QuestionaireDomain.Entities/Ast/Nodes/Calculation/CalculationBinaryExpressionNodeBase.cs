using System;
using QuestionaireDomain.Entities.Ast.Nodes.Calculation.Interfaces;
using QuestionaireDomain.Entities.Ast.Nodes.Common;
using QuestionaireDomain.Entities.DomainObjects;

namespace QuestionaireDomain.Entities.Ast.Nodes.Calculation
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