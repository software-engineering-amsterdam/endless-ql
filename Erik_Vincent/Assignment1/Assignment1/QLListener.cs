using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Assignment1
{
    internal class QLListener : QLBaseListener
    {
        public IEnumerable<QuestionForm> Forms => _forms.Reverse();

        private readonly Stack<QuestionForm> _forms = new Stack<QuestionForm>();
        private readonly Stack<Question> _questions = new Stack<Question>();
        private readonly Stack<Expression> _expressions = new Stack<Expression>();

        public override void EnterForm(QL.FormContext context)
        {
            _forms.Push(new QuestionForm(context.ID().ToString()));
        }

        public override void EnterQuestionBool(QL.QuestionBoolContext context)
        {
            var id = context.ID().ToString();
            var label = context.LABEL().ToString();
            Question question = new QuestionBool(id, label);
            _questions.Push(question);
        }

        public override void EnterQuestionMoney(QL.QuestionMoneyContext context)
        {
            var id = context.ID().ToString();
            var label = context.LABEL().ToString();
            Question question = new QuestionMoney(id, label);
            _questions.Push(question);
        }

        public override void ExitQuestionAssign(QL.QuestionAssignContext context)
        {
            _questions.Peek().Expression = _expressions.Pop();
        }

        public override void ExitQuestion(QL.QuestionContext context)
        {
            _forms.Peek().AddQuestion(_questions.Pop());
        }

        public override void ExitBool(QL.BoolContext context)
        {
            _expressions.Push(new Expression(context.TRUE() != null));
        }

        public override void ExitInteger(QL.IntegerContext context)
        {
            _expressions.Push(new Expression(int.Parse(context.INTEGER().GetText())));
        }

        public override void ExitDecimal(QL.DecimalContext context)
        {
            _expressions.Push(new Expression(Decimal.Parse(context.DECIMAL().GetText())));
        }

        public override void ExitId(QL.IdContext context)
        {
            _expressions.Push(new ExpressionId(context.ID().GetText(), _forms.Peek().Questions));
        }

        public override void ExitExpression(QL.ExpressionContext context)
        {
            var opFunc = GetOpFunc(context);
            if (opFunc == null) return;
            var right = _expressions.Pop();
            var left = _expressions.Pop();
            _expressions.Push(new ExpressionOperatorB(left, right, GetOpFunc(context)));
        }

        private static Func<Expression, Expression, dynamic> GetOpFunc(QL.ExpressionContext context)
        {
            if (context.AND() != null) return (l, r) => l.Evaluate() && r.Evaluate();
            if (context.OR() != null) return (l, r) => l.Evaluate() || r.Evaluate();
            if (context.SUB() != null) return (l, r) => l.Evaluate() - r.Evaluate();
            return null;
        }
    }
}
