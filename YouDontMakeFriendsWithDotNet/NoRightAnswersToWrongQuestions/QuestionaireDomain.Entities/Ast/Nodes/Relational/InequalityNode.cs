using System;
using QuestionnaireDomain.Entities.Ast.Nodes.Common.Interfaces;
using QuestionnaireDomain.Entities.Ast.Nodes.Relational.Interfaces;
using QuestionnaireDomain.Entities.Domain;

namespace QuestionnaireDomain.Entities.Ast.Nodes.Relational
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