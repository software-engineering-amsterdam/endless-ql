namespace QuestionaireDomain.Entities.API.AstNodes.Boolean
{
    public interface ILiteralNode : ITerminalNode
    {
        bool Value { get; }
    }
}