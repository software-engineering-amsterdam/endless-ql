using System;
using QuestionnaireDomain.Entities.Ast.Nodes.Common.Interfaces;

namespace QuestionnaireDomain.Entities.Ast.Nodes.Common
{
    internal class UntypedVariableNode :
        VariableNodeBase, 
        IUntypedVariableNode
    {
        public UntypedVariableNode(Guid id, string variableName)
            : base(id, variableName)
        {
        }
    }
}
