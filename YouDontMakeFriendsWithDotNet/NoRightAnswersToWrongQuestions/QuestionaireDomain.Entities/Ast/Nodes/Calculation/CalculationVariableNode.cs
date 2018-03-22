using System;
using QuestionnaireDomain.Entities.Ast.Nodes.Calculation.Interfaces;
using QuestionnaireDomain.Entities.Ast.Nodes.Common;

namespace QuestionnaireDomain.Entities.Ast.Nodes.Calculation
{
    internal class CalculationVariableNode : 
        VariableNodeBase, 
        ICalculationVariableNode
    {
        public CalculationVariableNode(Guid id, string variableName) 
            : base(id, variableName)
        {
        }
    }
}