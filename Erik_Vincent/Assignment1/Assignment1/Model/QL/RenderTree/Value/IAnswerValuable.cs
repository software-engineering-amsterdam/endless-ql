namespace Assignment1.Model.QL.RenderTree.Value
{
    public interface IAnswerValuable<out T>
    {
        T Value { get; }
        AnswerType Type { get; }
        bool IsUndefined { get; }
    }
}
