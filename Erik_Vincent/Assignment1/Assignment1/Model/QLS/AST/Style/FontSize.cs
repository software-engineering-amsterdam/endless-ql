namespace Assignment1.Model.QLS.AST.Style
{
    public class FontSize : IStyle
    {
        public int Value { get; }

        private int _value;

        public FontSize(int value)
        {
            _value = value;
        }

        public void Accept(IStyleVisitor visitor) => visitor.Visit(this);
    }
}
