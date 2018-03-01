namespace QuestionaireDomain.Entities.API.AstNodes.Calculation
{
    public interface IVariableNode : ITerminal
    {
        string VariableName { get; }
    }
}