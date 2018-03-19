namespace Assignment1.Model.QL.RenderTree.Value
{
    class AnswerValueUndefined : AnswerValue<bool>
    {
        public AnswerValueUndefined() : base(false, AnswerType.Undefined, true) { }
    }
}
