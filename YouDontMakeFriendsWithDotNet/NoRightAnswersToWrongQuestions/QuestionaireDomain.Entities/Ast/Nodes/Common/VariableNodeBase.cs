using System;
using QuestionaireDomain.Entities.Ast.Nodes.Common.Interfaces;

namespace QuestionaireDomain.Entities.Ast.Nodes.Common
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