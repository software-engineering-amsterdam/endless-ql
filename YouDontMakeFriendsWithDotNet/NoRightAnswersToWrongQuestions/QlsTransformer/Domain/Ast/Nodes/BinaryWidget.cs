namespace QlsTransformer.Domain.Ast.Nodes
{
    public abstract class BinaryWidget : IBinaryWidget
    {
        protected BinaryWidget(string trueText, string falseText)
        {
            TrueText = trueText;
            FalseText = falseText;
        }
        public string TrueText { get; }
        public string FalseText { get; }
    }
}