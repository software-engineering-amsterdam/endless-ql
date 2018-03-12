namespace QuestionaireDomain.Entities.Ast.Nodes.Calculation.Interfaces
{
    public interface INumberNode : ITerminal
    {
        decimal Value { get; }
    }
}