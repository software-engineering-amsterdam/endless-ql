namespace Assignment1.Model.QLS.AST.Style.Widget
{
    public abstract class YesNoWidget : IWidget
    {
        public string Yes { get; }
        public string No { get; }

        protected YesNoWidget(string yes, string no)
        {
            Yes = yes;
            No = no;
        }

        public abstract void Accept(IStyleVisitor visitor);
    }
}
