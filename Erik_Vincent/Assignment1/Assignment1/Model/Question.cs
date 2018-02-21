using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Assignment1
{
    public class Question : Content
    {
        public string Id { get; }
        public string Label { get; }
        public dynamic Value
        {
            get => _computed ? _expression.Evaluate() : _value;
            set => _value = value;
        }

        private dynamic _value;
        private readonly Expression _expression;
        private readonly bool _computed;

        public Question(string id, string label)
        {
            Id = id;
            Label = label;
        }

        public Question(string id, string label, Expression expression)
        {
            Id = id;
            Label = label;
            _computed = true;
            _expression = expression;
        }

        public virtual Control CreateControl() => new Label() { Text = Label, AutoSize = true };
    }

    internal class QuestionBool : Question
    {
        public QuestionBool(string id, string label) : base(id, label)
        {
            Value = false;
        }

        public override Control CreateControl() => new CheckBox() { Text = Label, AutoSize = true, Checked = Value };
    }

    internal class QuestionMoney : Question
    {
        public QuestionMoney(string id, string label) : base(id, label)
        {
            Value = 0.0;
        }

        public override Control CreateControl()
        {
            var panel = new FlowLayoutPanel() { AutoSize = true, AutoSizeMode = AutoSizeMode.GrowAndShrink, FlowDirection = FlowDirection.TopDown };
            panel.Controls.Add(base.CreateControl());
            panel.Controls.Add(new TextBox() { Text = Value.ToString() });
            return panel;
        }
    }
}
