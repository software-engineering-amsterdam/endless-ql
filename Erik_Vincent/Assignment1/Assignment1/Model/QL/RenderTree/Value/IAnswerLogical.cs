namespace Assignment1.Model.QL.RenderTree.Value
{
    interface IAnswerLogical
    {
        IAnswerValuable<bool> And<U>(IAnswerValuable<U> right);

        IAnswerValuable<bool> Or<U>(IAnswerValuable<U> right);

        IAnswerValuable<bool> Not();
    }
}
