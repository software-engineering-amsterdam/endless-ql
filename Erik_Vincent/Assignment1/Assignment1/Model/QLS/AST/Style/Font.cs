namespace Assignment1.Model.QLS.AST.Style
{
    public class Font : IStyle
    {
        public string Value { get; }

        private string _value;

        public Font(string value)
        {
            _value = value;
        }

        public void Accept(IStyleVisitor visitor) => visitor.Visit(this);
    }
}
