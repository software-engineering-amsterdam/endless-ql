using System;
using QuestionnaireDomain.Entities.Ast.Nodes.Common;
using QuestionnaireDomain.Entities.Ast.Nodes.Common.Interfaces;
using QuestionnaireDomain.Entities.Domain;

namespace QuestionnaireDomain.Entities.Ast.Nodes.Relational
{
    internal abstract class RelationalOperationNode : AstNodeBase
    {
        public DomainId<IAstNode> LeftExpression { get; }
        public DomainId<IAstNode> RightExpression { get; }

        protected RelationalOperationNode(
            Guid id, 
            string definition,
            DomainId<IAstNode> leftExpression,
            DomainId<IAstNode> rightExpression) : base(id, definition)
        {
            LeftExpression = leftExpression;
            RightExpression = rightExpression;
        }
    }
}