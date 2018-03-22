using Assignment1.Model.QL.AST;

namespace Assignment1.Model.QLS.AST.Style
{
    public class Width : ASTNode, IStyle
    {
        public int Value { get; }

        private int _value;

        public Width(int lineNumber, int value)
        {
            _lineNumber = lineNumber;
            _value = value;
        }

        public void Accept(IStyleVisitor visitor) => visitor.Visit(this);
    }
}
