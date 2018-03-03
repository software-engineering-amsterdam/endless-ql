using System;
using QuestionaireDomain.Entities.API;
using QuestionaireDomain.Entities.API.AstNodes;
using QuestionaireDomain.Entities.API.AstNodes.Relational;

namespace QuestionaireDomain.Entities.DomainObjects
{
    public class EqualityNode : AstNodeBase, IEqualityNode
    {
        public EqualityNode
            (Guid id,
            Reference<IAstNode> leftExpression,
            Reference<IAstNode> rightExpression) : base(id)
        {
            LeftExpression = leftExpression;
            RightExpression = rightExpression;
        }

        public override void Accept(IAstVisitor visitor)
        {
            throw new NotImplementedException();
        }

        public Reference<IAstNode> LeftExpression { get; }
        public Reference<IAstNode> RightExpression { get; }
    }
}
