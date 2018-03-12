using System;
using QuestionaireDomain.Entities.Ast.Nodes.Boolean.Interfaces;
using QuestionaireDomain.Entities.Ast.Nodes.Common;
using QuestionaireDomain.Entities.DomainObjects;

namespace QuestionaireDomain.Entities.Ast.Nodes.Boolean
{
    internal class NegateNode : AstNodeBase, INegateNode
    {
        public NegateNode(
            Guid id,
            string definition,
            Reference<IBooleanLogicNode> childExpression) 
            : base(id, definition)
        {
            Expression = childExpression;
        }

        public Reference<IBooleanLogicNode> Expression { get; set; }
    }
}