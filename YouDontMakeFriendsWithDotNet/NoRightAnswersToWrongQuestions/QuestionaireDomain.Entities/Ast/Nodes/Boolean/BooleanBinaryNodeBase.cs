using System;
using QuestionnaireDomain.Entities.Ast.Nodes.Boolean.Interfaces;
using QuestionnaireDomain.Entities.Ast.Nodes.Common;
using QuestionnaireDomain.Entities.DomainObjects;

namespace QuestionnaireDomain.Entities.Ast.Nodes.Boolean
{
    internal abstract class BooleanBinaryNodeBase : AstNodeBase
    {
        protected BooleanBinaryNodeBase(
            Guid id, 
            string definition, 
            Reference<IBooleanLogicNode> leftExpression, 
            Reference<IBooleanLogicNode> rightExpression) 
            : base(id, definition)
        {
            LeftExpression = leftExpression;
            RightExpression = rightExpression;
        }

        public Reference<IBooleanLogicNode> LeftExpression { get; }
        public Reference<IBooleanLogicNode> RightExpression { get; }
    }
}