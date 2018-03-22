namespace Assignment1.Model.QLS.AST.Style.Widget
{
    public class Radio : YesNoWidget
    {
        public Radio(int lineNumber, string yes, string no) : base(lineNumber, yes, no) { }

        public override void Accept(IStyleVisitor visitor) => visitor.Visit(this);
    }
}
