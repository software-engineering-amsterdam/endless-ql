namespace Assignment1.Model.QL.RenderTree.Value
{
    public interface IAnswerComparable
    {
        AnswerValue<bool> GreaterThan<U>(AnswerValue<U> right);

        AnswerValue<bool> GreaterThanOrEqual<U>(AnswerValue<U> right);

        AnswerValue<bool> LessThan<U>(AnswerValue<U> right);

        AnswerValue<bool> LessThanOrEqual<U>(AnswerValue<U> right);

        AnswerValue<bool> Equal<U>(AnswerValue<U> right);

        AnswerValue<bool> NotEqual<U>(AnswerValue<U> right);
    }
}
