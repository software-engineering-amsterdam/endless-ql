using System;
using QuestionaireDomain.Entities.Ast.Nodes.Common;
using QuestionaireDomain.Entities.Ast.Nodes.Common.Interfaces;
using QuestionaireDomain.Entities.DomainObjects;

namespace QuestionaireDomain.Entities.Ast.Nodes.Relational
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