using Assignment1.Model.QL.AST;
using Assignment1.Model.QL.AST.Expression;
using Assignment1.Model.QL.AST.Value;
using Assignment1.Rendering;

namespace Assignment1.Model.QL.RenderTree
{
    public class RenderableQuestionBool : RenderableQuestion
    {
        //public RenderableQuestionBool(string id, string label, bool computed) : this(id, label, new Undefined(), computed)  { }

        public RenderableQuestionBool(string id, string label, IExpression computation, bool computed) : 
            base(id, label, Type.Boolean, computation, computed) { }

        public override void Accept(IQuestionVisitor visitor)
        {
            visitor.Visit(this);
        }
    }
}
