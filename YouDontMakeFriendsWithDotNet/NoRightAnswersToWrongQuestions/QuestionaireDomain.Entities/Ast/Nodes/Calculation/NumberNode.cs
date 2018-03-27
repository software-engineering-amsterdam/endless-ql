using System;
using System.Collections.Generic;
using QuestionnaireDomain.Entities.Ast.Nodes.Calculation.Interfaces;
using QuestionnaireDomain.Entities.Ast.Nodes.Common;
using QuestionnaireDomain.Entities.Domain;

namespace QuestionnaireDomain.Entities.Ast.Nodes.Calculation
{
    internal class NumberNode : AstNodeBase, INumberNode
    {
        public decimal Value { get; }

        public NumberNode(Guid id, string numberText) : base(id, numberText)
        {
            Value = decimal.Parse(numberText);
        }

        public IEnumerable<Reference<ICalculationNode>> Children => new List<Reference<ICalculationNode>>();
    }
}