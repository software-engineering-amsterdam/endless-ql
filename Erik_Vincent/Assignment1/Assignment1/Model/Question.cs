using System;

namespace Assignment1.Model
{
    public interface IQuestionVisitor
    {
        void Visit(QuestionBool question);
        void Visit(QuestionInt question);
        void Visit(QuestionDate question);
        void Visit(QuestionDecimal question);
        void Visit(QuestionMoney question);
        void Visit(QuestionString question);
    }

    public abstract class Question
    {
        public string Id { get; }
        public string Label { get; }
        public dynamic Value
        {
            get => Computed ? Computation.Evaluate() : _value;
            set => _value = value;
        }

        private dynamic _value;
        public Expression Computation;
        public bool Computed => Computation != null;
        public Expression Condition;

        protected Question(string id, string label)
        {
            Id = id;
            Label = label;
        }

        public abstract void Accept(IQuestionVisitor visitor);

        public void AddCondition(Expression condition) => Condition = Condition == null ? condition :  new ExpressionAnd(condition, Condition);
    }

    public class QuestionBool : Question
    {
        public QuestionBool(string id, string label) : base(id, label)
        {
            Value = false;
        }

        public override void Accept(IQuestionVisitor visitor)
        {
            visitor.Visit(this);
        }
    }

    public class QuestionDate : Question
    {
        public QuestionDate(string id, string label) : base(id, label)
        {
            Value = DateTime.Today;
        }

        public override void Accept(IQuestionVisitor visitor)
        {
            visitor.Visit(this);
        }
    }

    public class QuestionDecimal : Question
    {
        public QuestionDecimal(string id, string label) : base(id, label)
        {
            Value = 0;
        }

        public override void Accept(IQuestionVisitor visitor)
        {
            visitor.Visit(this);
        }
    }

    public class QuestionInt : Question
    {
        public QuestionInt(string id, string label) : base(id, label)
        {
            Value = 0;
        }

        public override void Accept(IQuestionVisitor visitor)
        {
            visitor.Visit(this);
        }
    }

    public class QuestionMoney : Question
    {
        public QuestionMoney(string id, string label) : base(id, label)
        {
            Value = 0;
        }

        public override void Accept(IQuestionVisitor visitor)
        {
            visitor.Visit(this);
        }
    }

    public class QuestionString : Question
    {
        public QuestionString(string id, string label) : base(id, label)
        {
            Value = "";
        }

        public override void Accept(IQuestionVisitor visitor)
        {
            visitor.Visit(this);
        }
    }
}
