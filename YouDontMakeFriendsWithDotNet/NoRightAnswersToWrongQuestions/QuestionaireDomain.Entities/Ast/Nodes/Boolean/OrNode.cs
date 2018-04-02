using System;
using QuestionnaireDomain.Entities.Ast.Nodes.Boolean.Interfaces;
using QuestionnaireDomain.Entities.Domain;

namespace QuestionnaireDomain.Entities.Ast.Nodes.Boolean
{
    internal class OrNode : BooleanBinaryNodeBase, IOrNode
    {
        public OrNode(
            Guid id,
            string definition,
            DomainId<IBooleanLogicNode> leftExpression,
            DomainId<IBooleanLogicNode> rightExpression)
            : base(id, definition, leftExpression, rightExpression)
        {
        }
    }
}