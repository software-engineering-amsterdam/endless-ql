namespace QlsTransformer.Domain.Ast.Nodes
{
    public class AstRadioButton : BinaryWidget, IRadioButton
    {
        public AstRadioButton(
            string trueText,
            string falseText)
            : base(trueText, falseText)
        {
        }
    }
}