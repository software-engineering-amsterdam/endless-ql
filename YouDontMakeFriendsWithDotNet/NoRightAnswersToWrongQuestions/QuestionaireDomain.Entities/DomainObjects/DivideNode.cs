using System;
using QuestionaireDomain.Entities.API;
using QuestionaireDomain.Entities.API.AstNodes.Calculation;

namespace QuestionaireDomain.Entities.DomainObjects
{
//    public abstract class BinaryCalculationOperationBase : AstNodeBase

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

    public class AddNode : AstNodeBase, IAddNode
    {
        public string CalculationDefinition { get; }
        public Reference<ICalculationNode> LeftCalculation { get; }
        public Reference<ICalculationNode> RightCalculation { get; }

        public AddNode(
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

    public class SubtractNode : AstNodeBase, ISubtractNode
    {
        public string CalculationDefinition { get; }
        public Reference<ICalculationNode> LeftCalculation { get; }
        public Reference<ICalculationNode> RightCalculation { get; }

        public SubtractNode(
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