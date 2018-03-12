using System;
using QuestionaireDomain.Entities.Ast.Nodes.Calculation.Interfaces;
using QuestionaireDomain.Entities.Ast.Nodes.Common;

namespace QuestionaireDomain.Entities.Ast.Nodes.Calculation
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