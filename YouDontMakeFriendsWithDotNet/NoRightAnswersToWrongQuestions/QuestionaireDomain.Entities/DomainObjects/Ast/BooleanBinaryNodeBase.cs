using System;
using QuestionaireDomain.Entities.API.AstNodes.Boolean;

namespace QuestionaireDomain.Entities.DomainObjects.Ast
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