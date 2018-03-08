using System;
using QuestionaireDomain.Entities.API;
using QuestionaireDomain.Entities.API.AstNodes.Calculation;

namespace QuestionaireDomain.Entities.DomainObjects.Ast
{
    internal class NumberNode : AstNodeBase, INumberNode
    {
        public decimal Value { get; }

        public NumberNode(Guid id, string numberText) : base(id, numberText)
        {
            Value = decimal.Parse(numberText);
        }

        public override void Accept(IAstVisitor visitor)
        {
            throw new NotImplementedException();
        }
    }
}