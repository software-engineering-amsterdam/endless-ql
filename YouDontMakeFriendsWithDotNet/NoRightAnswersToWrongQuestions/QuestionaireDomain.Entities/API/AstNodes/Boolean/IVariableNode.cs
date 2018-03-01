namespace QuestionaireDomain.Entities.API.AstNodes.Boolean
{
    public interface IVariableNode : ITerminalNode
    {
        string VariableName { get; }
    }
}