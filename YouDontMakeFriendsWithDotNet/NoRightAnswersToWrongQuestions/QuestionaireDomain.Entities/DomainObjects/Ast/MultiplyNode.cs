using System;
using QuestionaireDomain.Entities.API;
using QuestionaireDomain.Entities.API.AstNodes.Calculation;

namespace QuestionaireDomain.Entities.DomainObjects.Ast
{
    internal class MultiplyNode : AstNodeBase, IMultiplyNode
    {
        public Reference<ICalculationNode> LeftCalculation { get; }
        public Reference<ICalculationNode> RightCalculation { get; }

        public MultiplyNode(
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