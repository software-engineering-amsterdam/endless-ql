using System;
using QuestionaireDomain.Entities.API.AstNodes;
using QuestionaireDomain.Entities.API.AstNodes.Relational;

namespace QuestionaireDomain.Entities.DomainObjects.Ast
{
    internal class InequalityNode : 
        RelationalOperationNode, 
        IInequalityNode
    {
        public InequalityNode(
            Guid id,
            string definition,
            Reference<IAstNode> leftExpression,
            Reference<IAstNode> rightExpression)
            : base(id, definition, leftExpression, rightExpression)
        {
        }
    }
}