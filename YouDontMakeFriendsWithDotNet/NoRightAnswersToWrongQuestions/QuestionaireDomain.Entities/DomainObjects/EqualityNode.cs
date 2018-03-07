using System;
using QuestionaireDomain.Entities.API;
using QuestionaireDomain.Entities.API.AstNodes;
using QuestionaireDomain.Entities.API.AstNodes.Relational;

namespace QuestionaireDomain.Entities.DomainObjects
{
    public class EqualityNode : RelationalOperationNode, IEqualityNode
    {
        public EqualityNode(
            Guid id,
            string definition,
            Reference<IAstNode> leftExpression,
            Reference<IAstNode> rightExpression) 
            : base(id, definition, leftExpression, rightExpression)
        {
        }
        
        public override void Accept(IAstVisitor visitor)
        {
            throw new NotImplementedException();
        }
    }
}
