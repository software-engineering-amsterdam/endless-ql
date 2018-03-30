using Assignment1.Model.QL.AST;
using Assignment1.Model.QL.AST.Expression;
using Assignment1.Model.QL.AST.Value;
using Assignment1.Rendering;

namespace Assignment1.Model.QL.RenderTree
{
    public abstract class RenderableQuestion
    {
        public string Id { get; }
        public string Label { get; }
        public Type Type { get; }
        public IExpression Computation;
        public bool Computed;
        public IExpression Condition;

        //protected RenderableQuestion(string id, string label)
        //{
        //    Id = id;
        //    Label = label;
        //}

        //protected RenderableQuestion(string id, string label, Type type, bool computed) : this(id, label, type, new Undefined(), computed) { }

        protected RenderableQuestion(string id, string label, Type type, IExpression computation, bool computed)
        {
            Id = id;
            Label = label;
            Type = type;
            Computation = computation;
            Computed = computed;
        }

        public abstract void Accept(IQuestionVisitor visitor);

        public void AddCondition(IExpression condition) => Condition = Condition == null ? condition :  new And(condition, Condition);

        public bool IsVisible(ExpressionEvaluator evaluator)
        {
            if (Condition == null) return false;
            QLBoolean evaluated = (QLBoolean)evaluator.EvaluateExpression(Condition);
            return evaluated.Value;
        }
    }
}
