using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using Assignment1.Model.QL;
using Assignment1.Model.QLS;

namespace Assignment1.Rendering
{
    internal class QuestionFormWithStyleRenderer : IQuestionFormRenderer, IContentVisitor
    {
        private QuestionFormRenderer _basicRenderer;
        private QuestionForm _questionForm;
        private Stylesheet _stylesheet;
        private Stack<GroupBox> _sectionGroupBoxes = new Stack<GroupBox>();

        private readonly TabControl _tabControl = new TabControl
        {
            AutoSize = true
        };

        public QuestionFormWithStyleRenderer(QuestionForm questionForm, Stylesheet stylesheet)
        {
            _questionForm = questionForm;
            _stylesheet = stylesheet;
            _basicRenderer = new QuestionFormRenderer(_questionForm);
        }

        private void UpdateControls()
        {
            _tabControl.SuspendLayout();
            _tabControl.Controls.Clear();
            foreach (var page in _stylesheet.Pages)
            {
                _tabControl.TabPages.Add(RenderPage(page));
            }
            _tabControl.ResumeLayout();
        }

        private TabPage RenderPage(Page page)
        {
            var result = new TabPage(page.Id);
            foreach (var section in page.Sections)
            {
                section.Accept(this);
                result.Controls.Add(_sectionGroupBoxes.Pop());
            }
            return result;
        }

        public Control Render()
        {
            return _tabControl;
        }

        public void Visit(Section section)
        {
            var result = new GroupBox { Text = section.Label };
            _sectionGroupBoxes.Push(result);
            foreach (var content in section.Contents)
            {
                content.Accept(this);
            }
        }

        public void Visit(QuestionStyle question)
        {
            
        }
    }
}
