using System;
using QuestionaireDomain.Entities.API;
using QuestionaireDomain.Entities.API.AstNodes.Calculation;

namespace QuestionaireDomain.Entities.DomainObjects
{
    public class MultiplyNode : AstNodeBase, IMultiplyNode
    {
        public string CalculationDefinition { get; }
        public Reference<ICalculationNode> LeftCalculation { get; }
        public Reference<ICalculationNode> RightCalculation { get; }

        public MultiplyNode(
            Guid id,
            string calculationDefinition,
            Reference<ICalculationNode> leftCalculation,
            Reference<ICalculationNode> rightCalculation) : base(id)
        {
            CalculationDefinition = calculationDefinition;
            LeftCalculation = leftCalculation;
            RightCalculation = rightCalculation;
        }

        public override void Accept(IAstVisitor visitor)
        {
            throw new NotImplementedException();
        }
    }

    public class DivideNode : AstNodeBase, IDivideNode
    {
        public string CalculationDefinition { get; }
        public Reference<ICalculationNode> LeftCalculation { get; }
        public Reference<ICalculationNode> RightCalculation { get; }

        public DivideNode(
            Guid id,
            string calculationDefinition,
            Reference<ICalculationNode> leftCalculation,
            Reference<ICalculationNode> rightCalculation) : base(id)
        {
            CalculationDefinition = calculationDefinition;
            LeftCalculation = leftCalculation;
            RightCalculation = rightCalculation;
        }

        public override void Accept(IAstVisitor visitor)
        {
            throw new NotImplementedException();
        }
    }

}