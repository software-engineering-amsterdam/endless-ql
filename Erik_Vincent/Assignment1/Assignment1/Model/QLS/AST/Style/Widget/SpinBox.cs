using Assignment1.Model.QL.AST;

namespace Assignment1.Model.QLS.AST.Style.Widget
{
    public class SpinBox : ASTNode, IWidget
    {
        public SpinBox(int lineNumber)
        {
            _lineNumber = lineNumber;
        }

        public void Accept(IStyleVisitor visitor) => visitor.Visit(this);
    }
}
