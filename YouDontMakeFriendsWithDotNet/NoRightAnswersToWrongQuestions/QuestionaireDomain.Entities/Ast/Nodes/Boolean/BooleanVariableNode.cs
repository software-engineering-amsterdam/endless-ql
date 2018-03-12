using System;
using QuestionaireDomain.Entities.Ast.Nodes.Boolean.Interfaces;
using QuestionaireDomain.Entities.Ast.Nodes.Common;

namespace QuestionaireDomain.Entities.Ast.Nodes.Boolean
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