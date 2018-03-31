using Assignment1.Model.QL.AST;

namespace Assignment1.Model.QLS.AST.Style.Widget
{
    public class Slider : ASTNode, IWidget
    {
        public Slider(int lineNumber)
        {
            _lineNumber = lineNumber;
        }

        public void Accept(IStyleVisitor visitor) => visitor.Visit(this);
    }
}
