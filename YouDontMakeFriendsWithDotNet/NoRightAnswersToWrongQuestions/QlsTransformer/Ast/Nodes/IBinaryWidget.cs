namespace QlsTransformer.Ast.Nodes
{
    public interface IBinaryWidget : IWidget
    {
        string TrueText { get; }
        string FalseText { get; }
    }
}