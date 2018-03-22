namespace QLParser.AST.Nodes
{
    public interface IExpressionNode
    {
        NodeType GetNodeType();

        QValueType GetQValueType();
    }
}