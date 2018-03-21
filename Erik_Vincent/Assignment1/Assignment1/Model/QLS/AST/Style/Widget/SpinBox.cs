namespace Assignment1.Model.QLS.AST.Style.Widget
{
    public class SpinBox : IWidget
    {
        public void Accept(IStyleVisitor visitor) => visitor.Visit(this);
    }
}
