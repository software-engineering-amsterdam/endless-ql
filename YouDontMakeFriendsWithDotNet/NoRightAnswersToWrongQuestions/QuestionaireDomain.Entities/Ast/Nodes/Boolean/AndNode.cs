using System;
using QuestionnaireDomain.Entities.Ast.Nodes.Boolean.Interfaces;
using QuestionnaireDomain.Entities.DomainObjects;

namespace QuestionnaireDomain.Entities.Ast.Nodes.Boolean
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