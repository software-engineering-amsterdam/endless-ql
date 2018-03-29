using Assignment1.Model.QL.AST;
using Assignment1.Model.QL.AST.Expression;
using Assignment1.Model.QL.AST.Value;

namespace Assignment1.Model.QL.RenderTree
{
    public class RenderableQuestionMoney : RenderableQuestion
    {
        public RenderableQuestionMoney(string id, string label, bool computed) : this(id, label, new Undefined(), computed) { }

        public RenderableQuestionMoney(string id, string label, IExpression computation, bool computed) : 
            base(id, label, Type.Money, computation, computed) { }

        public override void Accept(IQuestionVisitor visitor)
        {
            visitor.Visit(this);
        }
    }
}
