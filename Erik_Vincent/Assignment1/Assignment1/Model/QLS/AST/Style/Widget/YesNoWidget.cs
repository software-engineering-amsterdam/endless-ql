using Assignment1.Model.QL.AST;

namespace Assignment1.Model.QLS.AST.Style.Widget
{
    public abstract class YesNoWidget : ASTNode, IWidget
    {
        public string Yes { get; }
        public string No { get; }

        protected YesNoWidget(int lineNumber, string yes, string no)
        {
            _lineNumber = lineNumber;
            Yes = yes;
            No = no;
        }

        public abstract void Accept(IStyleVisitor visitor);
    }
}
