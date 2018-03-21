namespace Assignment1.Model.QL.RenderTree
{
    public enum AnswerType
    {
        Boolean,
        Integer,
        String,
        Decimal,
        Money,
        Date,
        Undefined
    }

    public static class AnswerTypeMethods
    {
        public static bool IsNumeric(this AnswerType at) => at == AnswerType.Integer || at == AnswerType.Money || at == AnswerType.Decimal;
    }
}
