namespace Assignment1.Model.QLS.AST.Style
{
    public class Font : Style
    {
        public string Value { get; }

        private string _value;

        public Font(int lineNumber, string value)
        {
            _lineNumber = lineNumber;
            _value = value;
        }

        public override void Accept(IStyleVisitor visitor) => visitor.Visit(this);
    }
}
