using Assignment1.Model.QL.AST;
using Assignment1.Model.QL.AST.Expression;
using Assignment1.Model.QL.AST.Value;

namespace Assignment1.Model.QL.RenderTree
{
    public class RenderableQuestionInt : RenderableQuestion
    {
        public RenderableQuestionInt(string id, string label, bool computed) : this(id, label, new Undefined(), computed) { }

        public RenderableQuestionInt(string id, string label, IExpression computation, bool computed) : 
            base(id, label, Type.Integer, computation, computed) { }

        public override void Accept(IQuestionVisitor visitor)
        {
            visitor.Visit(this);
        }
    }
}
