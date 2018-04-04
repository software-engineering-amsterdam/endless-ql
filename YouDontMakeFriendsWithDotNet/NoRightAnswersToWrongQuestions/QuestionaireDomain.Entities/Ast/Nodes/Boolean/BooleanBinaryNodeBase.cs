using System;
using QuestionnaireDomain.Entities.Ast.Nodes.Boolean.Interfaces;
using QuestionnaireDomain.Entities.Ast.Nodes.Common;
using QuestionnaireDomain.Entities.Domain;

namespace QuestionnaireDomain.Entities.Ast.Nodes.Boolean
{
    internal abstract class BooleanBinaryNodeBase : AstNodeBase
    {
        protected BooleanBinaryNodeBase(
            Guid id,
            string definition,
            DomainId<IBooleanLogicNode> leftExpression,
            DomainId<IBooleanLogicNode> rightExpression)
            : base(id, definition)
        {
            LeftExpression = leftExpression;
            RightExpression = rightExpression;
        }

        public DomainId<IBooleanLogicNode> LeftExpression { get; }
        public DomainId<IBooleanLogicNode> RightExpression { get; }
    }
}