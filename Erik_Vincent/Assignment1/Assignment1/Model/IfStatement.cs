using System.Collections.Generic;
using System.Windows.Forms;

namespace Assignment1
{
    public class IfStatement : Content
    {
        protected readonly Expression Expression;
        protected readonly List<Content> ThenContent;

        public IfStatement(Expression expression, List<Content> thenContent)
        {
            Expression = expression;
            ThenContent = thenContent;
        }

        public override Control CreateControl()
        {
            return !Expression.Evaluate() ? new Control() : CreatePanel(ThenContent);
        }

        protected Panel CreatePanel(List<Content> content)
        {
            var panel = new FlowLayoutPanel
            {
                FlowDirection = FlowDirection.TopDown,
                AutoSize = true
            };
            foreach (var c in content)
            {
                panel.Controls.Add(c.CreateControl());
            }
            return panel;
        }
    }

    public class IfElseStatement : IfStatement
    {
        private readonly List<Content> _elseContent;

        public IfElseStatement(Expression expression, List<Content> thenContent, List<Content> elseContent) : base(expression, thenContent)
        {
            _elseContent = elseContent;
        }

        public override Control CreateControl()
        {
            return CreatePanel(Expression.Evaluate() ? ThenContent : _elseContent);
        }
    }
}
