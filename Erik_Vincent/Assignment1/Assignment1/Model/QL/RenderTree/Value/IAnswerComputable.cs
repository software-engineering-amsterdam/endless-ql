namespace Assignment1.Model.QL.RenderTree.Value
{
    interface IAnswerComputable
    {
        IAnswerValuable Add<U>(AnswerValue<U> right);

        IAnswerValuable Subtract<U>(AnswerValue<U> right);

        IAnswerValuable Multiply<U>(AnswerValue<U> right);

        IAnswerValuable Divide<U>(AnswerValue<U> right);
    }
}
