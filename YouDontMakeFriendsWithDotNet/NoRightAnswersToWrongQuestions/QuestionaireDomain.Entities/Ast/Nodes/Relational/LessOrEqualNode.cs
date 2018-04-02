using System;
using QuestionnaireDomain.Entities.Ast.Nodes.Common.Interfaces;
using QuestionnaireDomain.Entities.Ast.Nodes.Relational.Interfaces;
using QuestionnaireDomain.Entities.Domain;

namespace QuestionnaireDomain.Entities.Ast.Nodes.Relational
{
    internal class LessOrEqualNode :
        RelationalOperationNode,
        ILessOrEqualNode
    {
        public LessOrEqualNode(
            Guid id,
            string definition,
            DomainId<IAstNode> leftExpression,
            DomainId<IAstNode> rightExpression)
            : base(id, definition, leftExpression, rightExpression)
        {
        }
    }
}