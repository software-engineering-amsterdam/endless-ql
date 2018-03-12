using System;
using QuestionaireDomain.Entities.Ast.Nodes.Boolean.Interfaces;
using QuestionaireDomain.Entities.DomainObjects;

namespace QuestionaireDomain.Entities.Ast.Nodes.Boolean
{
    internal class OrNode : BooleanBinaryNodeBase, IOrNode
    {
        public OrNode(
            Guid id,
            string definition,
            Reference<IBooleanLogicNode> leftExpression,
            Reference<IBooleanLogicNode> rightExpression) 
            : base(id, definition, leftExpression, rightExpression)
        {
        }
    }
}