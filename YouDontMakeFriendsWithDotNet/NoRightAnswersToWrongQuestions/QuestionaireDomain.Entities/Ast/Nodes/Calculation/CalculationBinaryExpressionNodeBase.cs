using System;
using System.Collections.Generic;
using QuestionnaireDomain.Entities.Ast.Nodes.Calculation.Interfaces;
using QuestionnaireDomain.Entities.Ast.Nodes.Common;
using QuestionnaireDomain.Entities.Domain;

namespace QuestionnaireDomain.Entities.Ast.Nodes.Calculation
{
    internal abstract class CalculationBinaryExpressionNodeBase :
        AstNodeBase
    {
        protected CalculationBinaryExpressionNodeBase(
            Guid id,
            string definition,
            DomainId<ICalculationNode> leftCalculation,
            DomainId<ICalculationNode> rightCalculation) : base(id, definition)
        {
            LeftCalculation = leftCalculation;
            RightCalculation = rightCalculation;
            Children = new[] {leftCalculation, rightCalculation};
        }

        public DomainId<ICalculationNode> LeftCalculation { get; }
        public DomainId<ICalculationNode> RightCalculation { get; }

        public IEnumerable<DomainId<ICalculationNode>> Children { get; }
    }
}