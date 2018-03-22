namespace Assignment1.Model.QLS.AST.Style
{
    public class Color : IStyle
    {
        public System.Drawing.Color Value { get; }

        private System.Drawing.Color _value;

        public Color(System.Drawing.Color value)
        {
            _value = value;
        }

        public void Accept(IStyleVisitor visitor) => visitor.Visit(this);
    }
}
