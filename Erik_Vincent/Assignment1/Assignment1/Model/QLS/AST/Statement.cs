using Assignment1.Model.QL.AST;

namespace Assignment1.Model.QLS.AST
{
    public abstract class Statement : ASTNode, IQLSASTNode
    {
        public abstract void Accept(IQLSASTVisitor visitor);
    }
}
