using System;
using QuestionaireDomain.Entities.Ast.Nodes.Calculation.Interfaces;
using QuestionaireDomain.Entities.Ast.Nodes.Common;

namespace QuestionaireDomain.Entities.Ast.Nodes.Calculation
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