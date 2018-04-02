using System;
using QuestionnaireDomain.Entities.Ast.Nodes.Common.Interfaces;

namespace QuestionnaireDomain.Entities.Ast.Nodes.Common
{
    internal abstract class VariableNodeBase :
        AstNodeBase,
        IVariableNode
    {
        protected VariableNodeBase(
            Guid id,
            string variableName) : base(id, variableName)
        {
            VariableName = variableName;
        }

        public string VariableName { get; }
    }
}