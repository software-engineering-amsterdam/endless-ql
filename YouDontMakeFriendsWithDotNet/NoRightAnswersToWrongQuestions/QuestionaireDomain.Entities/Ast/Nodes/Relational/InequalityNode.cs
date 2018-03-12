using System;
using QuestionaireDomain.Entities.Ast.Nodes.Common.Interfaces;
using QuestionaireDomain.Entities.Ast.Nodes.Relational.Interfaces;
using QuestionaireDomain.Entities.DomainObjects;

namespace QuestionaireDomain.Entities.Ast.Nodes.Relational
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