using System;
using QuestionnaireDomain.Entities.Ast.Nodes.Boolean.Interfaces;
using QuestionnaireDomain.Entities.Domain;

namespace QuestionnaireDomain.Entities.Ast.Nodes.Boolean
{
    internal class AndNode : BooleanBinaryNodeBase, IAndNode
    {
        public AndNode(
            Guid id,
            string definition,
            DomainId<IBooleanLogicNode> leftExpression,
            DomainId<IBooleanLogicNode> rightExpression)
            : base(id, definition, leftExpression, rightExpression)
        {
        }
    }
}