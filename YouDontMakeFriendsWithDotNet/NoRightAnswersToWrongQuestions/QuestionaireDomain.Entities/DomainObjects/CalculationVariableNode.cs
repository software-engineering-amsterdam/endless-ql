using System;
using QuestionaireDomain.Entities.API;
using QuestionaireDomain.Entities.API.AstNodes.Calculation;

namespace QuestionaireDomain.Entities.DomainObjects
{
    public class CalculationVariableNode : AstNodeBase, IVariableNode
    {
        public string VariableName { get; }

        public CalculationVariableNode(Guid id, string variableName) 
            : base(id, variableName)
        {
            VariableName = variableName;
        }

        public override void Accept(IAstVisitor visitor)
        {
            throw new NotImplementedException();
        }
    }
}