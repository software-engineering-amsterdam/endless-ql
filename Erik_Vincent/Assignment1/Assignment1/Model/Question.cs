using System;

namespace Assignment1.Model
{
    public abstract class Question : Content
    {
        public string Id { get; }
        public string Label { get; }
        public dynamic Value
        {
            get => Computed ? Expression.Evaluate() : _value;
            set => _value = value;
        }

        private dynamic _value;
        public Expression Expression;
        public bool Computed;

        protected Question(string id, string label)
        {
            Id = id;
            Label = label;
        }
    }

    public class QuestionBool : Question
    {
        public QuestionBool(string id, string label) : base(id, label)
        {
            Value = false;
        }

        public override void Accept(IContentVisitor visitor)
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

        public override void Accept(IContentVisitor visitor)
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

        public override void Accept(IContentVisitor visitor)
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

        public override void Accept(IContentVisitor visitor)
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

        public override void Accept(IContentVisitor visitor)
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

        public override void Accept(IContentVisitor visitor)
        {
            visitor.Visit(this);
        }
    }
}
