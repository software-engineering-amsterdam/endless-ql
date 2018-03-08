using System;
using QuestionaireDomain.Entities.API;
using QuestionaireDomain.Entities.API.AstNodes.Boolean;

namespace QuestionaireDomain.Entities.DomainObjects.Ast
{
    internal class AndNode : AstNodeBase, IAndNode
    {
        public AndNode(
            Guid id,
            string definition,
            Reference<IBooleanLogicNode> leftExpression,
            Reference<IBooleanLogicNode> rightExpression) : base(id, definition)
        {
            LeftExpression = leftExpression;
            RightExpression = rightExpression;
        }

        public override void Accept(IAstVisitor visitor)
        {
            (visitor as IAstVisitor<IAndNode>)?.Visit(this);
        }

        public Reference<IBooleanLogicNode> LeftExpression { get; }
        public Reference<IBooleanLogicNode> RightExpression { get; }
    }
}
