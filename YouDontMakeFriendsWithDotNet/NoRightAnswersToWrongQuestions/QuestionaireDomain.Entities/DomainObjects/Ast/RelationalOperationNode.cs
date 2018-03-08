using System;
using QuestionaireDomain.Entities.API.AstNodes;

namespace QuestionaireDomain.Entities.DomainObjects.Ast
{
    internal abstract class RelationalOperationNode : AstNodeBase
    {
        public Reference<IAstNode> LeftExpression { get; }
        public Reference<IAstNode> RightExpression { get; }

        protected RelationalOperationNode(
            Guid id, 
            string definition,
            Reference<IAstNode> leftExpression,
            Reference<IAstNode> rightExpression) : base(id, definition)
        {
            LeftExpression = leftExpression;
            RightExpression = rightExpression;
        }
    }
}