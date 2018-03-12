using System;
using QuestionaireDomain.Entities.API.AstNodes;

namespace QuestionaireDomain.Entities.DomainObjects.Ast
{
    internal abstract class VariableNodeBase : 
        AstNodeBase, 
        IVariableNode
    {
        public string VariableName { get; }

        protected VariableNodeBase(
            Guid id, 
            string variableName) : base(id, variableName)
        {
            VariableName = variableName;
        }
    }
}