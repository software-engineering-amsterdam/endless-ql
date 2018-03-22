using Assignment1.Model.QL.AST;

namespace Assignment1.Model.QLS.AST.Style
{
    public abstract class Style : ASTNode, IStyle
    {
        public abstract void Accept(IStyleVisitor visitor);
    }
}
