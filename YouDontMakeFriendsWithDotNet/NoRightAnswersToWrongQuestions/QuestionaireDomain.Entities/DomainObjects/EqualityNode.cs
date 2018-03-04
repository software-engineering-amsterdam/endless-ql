using System;
using QuestionaireDomain.Entities.API;
using QuestionaireDomain.Entities.API.AstNodes;
using QuestionaireDomain.Entities.API.AstNodes.Relational;

namespace QuestionaireDomain.Entities.DomainObjects
{
    public class EqualityNode : AstNodeBase, IEqualityNode
    {
        public Reference<IAstNode> LeftExpression { get; }
        public Reference<IAstNode> RightExpression { get; }

        public EqualityNode(
            Guid id,
            string definition,
            Reference<IAstNode> leftExpression,
            Reference<IAstNode> rightExpression) : base(id, definition)
        {
            LeftExpression = leftExpression;
            RightExpression = rightExpression;
        }
        
        public override void Accept(IAstVisitor visitor)
        {
            throw new NotImplementedException();
        }
    }
}
