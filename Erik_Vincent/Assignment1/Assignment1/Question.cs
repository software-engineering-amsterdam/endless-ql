using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Assignment1
{
    internal abstract class Question
    {
        public virtual string Id { get; }
        public virtual string Label { get; }

        protected Question(string id, string label)
        {
            Id = id;
            Label = label;
        }
    }

    internal class QuestionBool : Question
    {
        public bool Value { get; }
        public QuestionBool(string id, string label, bool value = false) : base(id, label)
        {
            Value = value;
        }
    }

    internal class QuestionDecimal : Question
    {
        public QuestionDecimal(string id, string label, double value = 0.0) : base(id, label)
        {

        }
    }
}
