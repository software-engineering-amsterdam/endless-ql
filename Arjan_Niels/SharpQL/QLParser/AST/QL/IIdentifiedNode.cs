namespace QLParser.AST.QL
{
    public interface IIdentifiedNode
    {
        string ID { get; }
        string Text { get; }
        QValueType ValueType { get; }
    }
}
