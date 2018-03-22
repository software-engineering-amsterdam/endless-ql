namespace QLParser.AST.QL
{
    public interface IExpressionNode
    {
        NodeType GetNodeType();

        QValueType GetQValueType();
    }
}