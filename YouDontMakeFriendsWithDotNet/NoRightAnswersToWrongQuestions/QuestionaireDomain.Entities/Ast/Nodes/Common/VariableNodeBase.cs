using System;
using QuestionnaireDomain.Entities.Ast.Nodes.Common.Interfaces;

namespace QuestionnaireDomain.Entities.Ast.Nodes.Common
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