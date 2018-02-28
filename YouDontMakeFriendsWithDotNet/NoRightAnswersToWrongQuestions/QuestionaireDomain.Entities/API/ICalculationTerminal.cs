namespace QuestionaireDomain.Entities.API
{
    public interface ICalculationNode
    {
    }

    public interface ICalculationTerminal : ICalculationNode
    {
    }

    public interface ICalculationNonTerminal : ICalculationNode
    {
    }
    
    public interface INumberAstNode : IAstNode, ICalculationTerminal
    {
    }

    public interface INumberVariableNode : IAstNode, ICalculationTerminal
    {
    }

    public interface ICalculationExpressionNode : IAstNode, ICalculationNonTerminal
    {
    }

    public interface IBinaryCalculationExpressionNode : ICalculationExpressionNode
    {
        ICalculationNode LeftCalculation { get; set; }
        ICalculationNode RightCalculation { get; set; }
    }

    public interface IAddNode : IBinaryCalculationExpressionNode
    {
    }

    public interface ISubtractNode : IBinaryCalculationExpressionNode
    {
    }

    public interface IMultiplyNode : IBinaryCalculationExpressionNode
    {
    }

    public interface IDivideNode : IBinaryCalculationExpressionNode
    {
    }
}
