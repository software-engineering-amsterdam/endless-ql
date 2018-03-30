namespace Assignment1.Model.QLS.AST.Style.Widget
{
    public class DropDown : YesNoWidget
    {
        public DropDown(int lineNumber, string yes, string no) : base(lineNumber, yes, no) { }

        public override void Accept(IStyleVisitor visitor) => visitor.Visit(this);
    }
}
