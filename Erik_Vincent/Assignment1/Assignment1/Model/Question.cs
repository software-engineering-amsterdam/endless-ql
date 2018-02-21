using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Assignment1
{
    internal class Question : Content
    {
        public string Id { get; }
        public string Label { get; }
        public Expression Expression { get; set; }

        public Question(string id, string label)
        {
            Id = id;
            Label = label;
        }

        public virtual Control CreateControl() => new Label() { Text = Label, AutoSize = true };
    }

    internal class QuestionBool : Question
    {
        public QuestionBool(string id, string label) : base(id, label)
        {
            Expression = new Expression(false);
        }

        public override Control CreateControl() => new CheckBox() { Text = Label, AutoSize = true, Checked = Expression.Evaluate() };
    }

    internal class QuestionMoney : Question
    {
        public QuestionMoney(string id, string label) : base(id, label)
        {
            Expression = new Expression(0.0);
        }

        public override Control CreateControl()
        {
            var panel = new FlowLayoutPanel() { AutoSize = true, AutoSizeMode = AutoSizeMode.GrowAndShrink, FlowDirection = FlowDirection.TopDown};
            panel.Controls.Add(base.CreateControl());
            panel.Controls.Add(new TextBox(){Text = Expression.Evaluate().ToString()});
            return panel;
        }
    }
}
