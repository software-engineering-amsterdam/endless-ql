namespace Assignment1.Model.QL.RenderTree.Value
{
    public interface IAnswerComparable
    {
        IAnswerValuable<bool> GreaterThan<U>(IAnswerValuable<U> right);

        IAnswerValuable<bool> GreaterThanOrEqual<U>(IAnswerValuable<U> right);

        IAnswerValuable<bool> LessThan<U>(IAnswerValuable<U> right);

        IAnswerValuable<bool> LessThanOrEqual<U>(IAnswerValuable<U> right);

        IAnswerValuable<bool> Equal<U>(IAnswerValuable<U> right);

        IAnswerValuable<bool> NotEqual<U>(IAnswerValuable<U> right);
    }
}
