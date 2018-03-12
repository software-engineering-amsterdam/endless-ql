using System;
using QuestionaireDomain.Entities.API.AstNodes.Boolean;

namespace QuestionaireDomain.Entities.DomainObjects.Ast
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