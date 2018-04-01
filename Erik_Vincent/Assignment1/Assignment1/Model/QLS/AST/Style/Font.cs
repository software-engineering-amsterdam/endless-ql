using Assignment1.Model.QL.AST;

namespace Assignment1.Model.QLS.AST.Style
{
    public class Font : ASTNode, IStyle
    {
        public string Value { get; }

        public Font(int lineNumber, string value)
        {
            _lineNumber = lineNumber;
            Value = value;
        }

        public void Accept(IStyleVisitor visitor) => visitor.Visit(this);
    }
}
