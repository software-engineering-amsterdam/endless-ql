using System;
using QuestionaireDomain.Entities.API.AstNodes.Boolean;

namespace QuestionaireDomain.Entities.DomainObjects.Ast
{
    internal class AndNode : BooleanBinaryNodeBase, IAndNode
    {
        public AndNode(
            Guid id,
            string definition,
            Reference<IBooleanLogicNode> leftExpression,
            Reference<IBooleanLogicNode> rightExpression) 
            : base(id, definition, leftExpression, rightExpression)
        {
        }
    }
}