namespace QLParser.AST.QL
{
    public interface IQuestionable
    {
        string ID { get; }
        string Text { get; }
        QValueType ValueType { get; }
    }
}
