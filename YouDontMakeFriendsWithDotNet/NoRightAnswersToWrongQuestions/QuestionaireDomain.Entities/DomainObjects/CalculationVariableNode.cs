using System;
using QuestionaireDomain.Entities.API;
using QuestionaireDomain.Entities.API.AstNodes.Calculation;

namespace QuestionaireDomain.Entities.DomainObjects
{
    public class CalculationVariableNode : VariableNodeBase, ICalcualtionVariableNode
    {
        public CalculationVariableNode(Guid id, string variableName) 
            : base(id, variableName)
        {
        }

        public override void Accept(IAstVisitor visitor)
        {
            throw new NotImplementedException();
        }
    }
}