namespace Assignment1.Model.QL.Value
{
    class AnswerValueUndefined : AnswerValue<bool>
    {
        public AnswerValueUndefined() : base(false, AnswerType.Undefined, true) { }
    }
}
