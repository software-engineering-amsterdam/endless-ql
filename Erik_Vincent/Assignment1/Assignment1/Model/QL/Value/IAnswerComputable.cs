namespace Assignment1.Model.QL.Value
{
    interface IAnswerComputable<T>
    {
        IAnswerValuable<T> Add<U>(IAnswerValuable<U> right);

        IAnswerValuable<T> Subtract<U>(IAnswerValuable<U> right);

        IAnswerValuable<T> Multiply<U>(IAnswerValuable<U> right);

        IAnswerValuable<T> Divide<U>(IAnswerValuable<U> right);
    }
}
