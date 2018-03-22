namespace Assignment1.Model.QLS.AST.Style
{
    public class Width : Style
    {
        public int Value { get; }

        private int _value;

        public Width(int lineNumber, int value)
        {
            _lineNumber = lineNumber;
            _value = value;
        }

        public override void Accept(IStyleVisitor visitor) => visitor.Visit(this);
    }
}
