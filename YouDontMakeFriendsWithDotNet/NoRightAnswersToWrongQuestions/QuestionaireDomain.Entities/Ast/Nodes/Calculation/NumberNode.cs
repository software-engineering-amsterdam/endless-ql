using System;
using System.Collections.Generic;
using QuestionnaireDomain.Entities.Ast.Nodes.Calculation.Interfaces;
using QuestionnaireDomain.Entities.Ast.Nodes.Common;
using QuestionnaireDomain.Entities.Domain;

namespace QuestionnaireDomain.Entities.Ast.Nodes.Calculation
{
    internal class NumberNode : AstNodeBase, INumberNode
    {
        public NumberNode(Guid id, string numberText) : base(id, numberText)
        {
            Value = decimal.Parse(numberText);
        }

        public decimal Value { get; }

        public IEnumerable<DomainId<ICalculationNode>> Children => new List<DomainId<ICalculationNode>>();
    }
}