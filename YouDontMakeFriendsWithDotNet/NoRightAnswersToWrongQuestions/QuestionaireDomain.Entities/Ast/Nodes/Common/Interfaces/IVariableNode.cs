namespace QuestionaireDomain.Entities.Ast.Nodes.Common.Interfaces
{
    public interface IVariableNode : IAstNode
    {
        string VariableName { get; }
    }
}