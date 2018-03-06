namespace QuestionaireDomain.Entities.API.AstNodes
{
    public interface IVariableNode : IAstNode
    {
        string VariableName { get; }
    }
}