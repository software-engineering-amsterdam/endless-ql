namespace Assignment1.Model.QL.RenderTree.Value
{
    interface IAnswerLogical
    {
        AnswerValue<bool> And<U>(AnswerValue<U> right);

        AnswerValue<bool> Or<U>(AnswerValue<U> right);

        AnswerValue<bool> Not();
    }
}
