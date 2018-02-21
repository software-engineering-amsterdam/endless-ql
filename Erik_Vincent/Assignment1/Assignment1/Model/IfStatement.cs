using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Assignment1.Model
{
    class IfStatement : Statement
    {
        private Expression _condition;
        private List<Content> _thenContent;
        private List<Content> _elseContent;
        public Expression Condition
        {
            get
            {
                return _condition;
            }
        }
        public List<Content> ThenContent
        {
            get
            {
                return _thenContent;
            }
        }
        public List<Content> ElseContent
        {
            get
            {
                return _elseContent;
            }
        }

        public IfStatement(Expression condition, bool hasElseContent)
        {
            _condition = condition;
            _thenContent = new List<Content>();
            if (hasElseContent)
                _elseContent = new List<Content>();
        }

        public void AddThenContent(Content newContent)
        {
            _thenContent.Add(newContent);
        }

        public bool AddElseContent(Content newContent)
        {
            if (_elseContent == null)
                return false;
            _elseContent.Add(newContent);
            return true;
        }

        public override Control CreateControl()
        {
            FlowLayoutPanel panel = (FlowLayoutPanel)base.CreateControl();
            if (_condition.Evaluate())
            {
                addContentToPanel(panel, _thenContent);
            }
            else if (_elseContent.Count > 0)
            {
                addContentToPanel(panel, _elseContent);
            }
            else
            {
                panel.Visible = false;
            }
            return panel;
        }

        private void addContentToPanel(FlowLayoutPanel panel, List<Content> content)
        {
            //foreach (Content contentItem in content)
            foreach (Question question in content.OfType<Question>())
            {
                panel.Controls.Add(question.CreateControl());
            }
        }
    }
}
