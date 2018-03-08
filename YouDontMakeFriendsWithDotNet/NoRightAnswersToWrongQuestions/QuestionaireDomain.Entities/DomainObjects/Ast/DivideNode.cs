using System;
using QuestionaireDomain.Entities.API;
using QuestionaireDomain.Entities.API.AstNodes.Calculation;

namespace QuestionaireDomain.Entities.DomainObjects.Ast
{
    internal class DivideNode : AstNodeBase, IDivideNode
    {
        public Reference<ICalculationNode> LeftCalculation { get; }
        public Reference<ICalculationNode> RightCalculation { get; }

        public DivideNode(
            Guid id,
            string definition,
            Reference<ICalculationNode> leftCalculation,
            Reference<ICalculationNode> rightCalculation) : base(id, definition)
        {
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
        public Reference<ICalculationNode> LeftCalculation { get; }
        public Reference<ICalculationNode> RightCalculation { get; }

        public AddNode(
            Guid id,
            string definition,
            Reference<ICalculationNode> leftCalculation,
            Reference<ICalculationNode> rightCalculation) : base(id, definition)
        {
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
        public Reference<ICalculationNode> LeftCalculation { get; }
        public Reference<ICalculationNode> RightCalculation { get; }

        public SubtractNode(
            Guid id,
            string definition,
            Reference<ICalculationNode> leftCalculation,
            Reference<ICalculationNode> rightCalculation) : base(id, definition)
        {
            LeftCalculation = leftCalculation;
            RightCalculation = rightCalculation;
        }

        public override void Accept(IAstVisitor visitor)
        {
            throw new NotImplementedException();
        }
    }
}