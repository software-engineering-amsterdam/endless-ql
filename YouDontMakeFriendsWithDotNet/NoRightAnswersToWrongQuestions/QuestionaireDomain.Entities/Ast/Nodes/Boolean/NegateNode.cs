using System;
using QuestionnaireDomain.Entities.Ast.Nodes.Boolean.Interfaces;
using QuestionnaireDomain.Entities.Ast.Nodes.Common;
using QuestionnaireDomain.Entities.DomainObjects;

namespace QuestionnaireDomain.Entities.Ast.Nodes.Boolean
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