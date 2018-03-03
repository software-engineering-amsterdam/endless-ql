using System;
using QuestionaireDomain.Entities.API;
using QuestionaireDomain.Entities.API.AstNodes;
using QuestionaireDomain.Entities.API.AstNodes.Calculation;

namespace QuestionaireDomain.Entities.DomainObjects
{
    public class CalculationVariableNode : AstNodeBase, IVariableNode
    {
        public string VariableName { get; }
        public string CalculationDefinition { get; }

        public CalculationVariableNode(Guid id, string variableName) : base(id)
        {
            CalculationDefinition = variableName;
            VariableName = variableName;
        }

        public override void Accept(IAstVisitor visitor)
        {
            throw new NotImplementedException();
        }
    }
}