namespace QlsTransformer.Ast.Nodes
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