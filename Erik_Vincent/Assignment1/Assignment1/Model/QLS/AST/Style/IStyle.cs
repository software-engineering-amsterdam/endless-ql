namespace Assignment1.Model.QLS.AST.Style
{
    public interface IStyle
    {
        void Accept(IStyleVisitor visitor);
    }
}
