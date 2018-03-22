namespace Assignment1.Model.QLS.AST.Style
{
    public class FontSize : Style
    {
        public int Value { get; }

        private int _value;

        public FontSize(int lineNumber, int value)
        {
            _lineNumber = lineNumber;
            _value = value;
        }

        public override void Accept(IStyleVisitor visitor) => visitor.Visit(this);
    }
}
