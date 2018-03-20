using System;
using QuestionnaireDomain.Entities.Ast.Nodes.Boolean.Interfaces;
using QuestionnaireDomain.Entities.Ast.Nodes.Common;

namespace QuestionnaireDomain.Entities.Ast.Nodes.Boolean
{
    internal class BooleanVariableNode : 
        VariableNodeBase, 
        IBooleanVariableNode
    {
        public BooleanVariableNode(
            Guid id, 
            string variableName) 
            : base(id, variableName)
        {
        }
    }
}