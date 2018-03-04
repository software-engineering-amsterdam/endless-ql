using System;
using QuestionaireDomain.Entities.API;
using QuestionaireDomain.Entities.API.AstNodes.Boolean;

namespace QuestionaireDomain.Entities.DomainObjects
{
    public class NegateNode : AstNodeBase, INegateNode
    {
        public NegateNode(
            Guid id,
            Reference<IBooleanLogicNode> childExpression) : base(id)
        {
            Expression = childExpression;
        }

        public override void Accept(IAstVisitor visitor)
        {
            (visitor as IAstVisitor<INegateNode>)?.Visit(this);
        }

        public Reference<IBooleanLogicNode> Expression { get; set; }
    }
}