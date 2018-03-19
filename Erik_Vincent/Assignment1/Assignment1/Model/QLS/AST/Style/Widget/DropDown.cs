namespace Assignment1.Model.QLS.AST.Style.Widget
{
    public class DropDown : YesNoWidget
    {
        public DropDown(string yes, string no) : base(yes, no) { }

        public override void Accept(IStyleVisitor visitor) => visitor.Visit(this);
    }
}
