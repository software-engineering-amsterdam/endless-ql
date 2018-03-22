using System;
using QuestionnaireDomain.Entities.Ast.Nodes.Calculation.Interfaces;
using QuestionnaireDomain.Entities.Ast.Nodes.Common;

namespace QuestionnaireDomain.Entities.Ast.Nodes.Calculation
{
    internal class NumberNode : AstNodeBase, INumberNode
    {
        public decimal Value { get; }

        public NumberNode(Guid id, string numberText) : base(id, numberText)
        {
            Value = decimal.Parse(numberText);
        }
    }
}