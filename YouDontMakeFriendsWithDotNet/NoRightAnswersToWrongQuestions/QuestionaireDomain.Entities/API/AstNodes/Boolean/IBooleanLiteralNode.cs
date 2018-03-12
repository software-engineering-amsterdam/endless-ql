namespace QuestionaireDomain.Entities.API.AstNodes.Boolean
{
    public interface IBooleanLiteralNode : ITerminalNode
    {
        bool Value { get; }
    }
}