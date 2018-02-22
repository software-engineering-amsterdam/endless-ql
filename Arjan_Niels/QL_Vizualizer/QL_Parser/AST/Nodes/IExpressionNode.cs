namespace QL_Parser.AST.Nodes
{
    public interface IExpressionNode
    {
        NodeType GetNodeType();
        QValueType GetQValueType();
    }
}
