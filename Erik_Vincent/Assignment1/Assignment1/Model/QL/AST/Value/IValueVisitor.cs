namespace Assignment1.Model.QL.AST.Value
{
    public interface IValueVisitor
    {
        void Visit(QLBoolean value);
        void Visit(QLInteger value);
        void Visit(QLString value);
        void Visit(QLDate value);
        void Visit(QLDecimal value);
        void Visit(QLMoney value);
    }
}
