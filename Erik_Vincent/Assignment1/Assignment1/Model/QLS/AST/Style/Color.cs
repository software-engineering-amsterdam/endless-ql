using Assignment1.Model.QL.AST;

namespace Assignment1.Model.QLS.AST.Style
{
    public class Color : ASTNode, IStyle
    {
        public System.Drawing.Color Value { get; }

        private System.Drawing.Color _value;

        public Color(int lineNumber, System.Drawing.Color value)
        {
            _lineNumber = lineNumber;
            _value = value;
        }

        public void Accept(IStyleVisitor visitor) => visitor.Visit(this);
    }
}
