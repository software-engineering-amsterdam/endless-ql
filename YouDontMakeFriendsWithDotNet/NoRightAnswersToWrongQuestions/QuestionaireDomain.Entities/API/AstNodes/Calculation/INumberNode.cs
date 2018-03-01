namespace QuestionaireDomain.Entities.API.AstNodes.Calculation
{
    public interface INumberNode : ITerminal
    {
        decimal Value { get; }
    }
}