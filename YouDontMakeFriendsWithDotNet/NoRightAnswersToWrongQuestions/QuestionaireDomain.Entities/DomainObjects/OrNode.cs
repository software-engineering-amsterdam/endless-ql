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
            throw new NotImplementedException();
        }

        public Reference<IBooleanLogicNode> Expression { get; set; }
    }

    public class OrNode : AstNodeBase, IOrNode
    {
        public OrNode(
            Guid id,
            Reference<IBooleanLogicNode> leftExpression,
            Reference<IBooleanLogicNode> rightExpression) : base(id)
        {
            LeftExpression = leftExpression;
            RightExpression = rightExpression;
        }

        public override void Accept(IAstVisitor visitor)
        {
            throw new NotImplementedException();
        }

        public Reference<IBooleanLogicNode> LeftExpression { get; }
        public Reference<IBooleanLogicNode> RightExpression { get; }
    }
}