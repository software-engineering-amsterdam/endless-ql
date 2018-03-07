package astvisitor;

public interface IArithmeticValue{
    IntValue divide(IntValue left, IntValue right);
    IntValue divide(NumValue left, NumValue right);
}