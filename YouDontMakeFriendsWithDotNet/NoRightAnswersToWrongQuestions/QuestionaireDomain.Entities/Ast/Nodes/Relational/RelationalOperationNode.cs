using System;
using QuestionnaireDomain.Entities.Ast.Nodes.Common;
using QuestionnaireDomain.Entities.Ast.Nodes.Common.Interfaces;
using QuestionnaireDomain.Entities.DomainObjects;

namespace QuestionnaireDomain.Entities.Ast.Nodes.Relational
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