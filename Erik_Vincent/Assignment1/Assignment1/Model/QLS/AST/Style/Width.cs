namespace Assignment1.Model.QLS.AST.Style
{
    public class Width : IStyle
    {
        public int Value { get; }

        private int _value;

        public Width(int value)
        {
            _value = value;
        }

        public void Accept(IStyleVisitor visitor) => visitor.Visit(this);
    }
}
