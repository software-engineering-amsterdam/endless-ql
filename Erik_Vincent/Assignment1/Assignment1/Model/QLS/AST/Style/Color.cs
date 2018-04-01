using Assignment1.Model.QL.AST;

namespace Assignment1.Model.QLS.AST.Style
{
    public class Color : ASTNode, IStyle
    {
        public System.Drawing.Color Value { get; }

        public Color(int lineNumber, System.Drawing.Color value)
        {
            _lineNumber = lineNumber;
            Value = value;
        }

        public void Accept(IStyleVisitor visitor) => visitor.Visit(this);
    }
}
