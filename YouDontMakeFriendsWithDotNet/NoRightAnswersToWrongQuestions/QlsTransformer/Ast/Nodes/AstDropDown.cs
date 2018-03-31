namespace QlsTransformer.Ast.Nodes
{
    public class AstDropDown : BinaryWidget, IDropDown
    {
        public AstDropDown(
            string trueText,
            string falseText)
            : base(trueText, falseText)
        {
        }
    }
}