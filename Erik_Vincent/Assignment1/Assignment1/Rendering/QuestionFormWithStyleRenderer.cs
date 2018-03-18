using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using Assignment1.Model.QL;
using Assignment1.Model.QLS;
using Assignment1.Model.QLS.AST;

namespace Assignment1.Rendering
{
    internal class QuestionFormWithStyleRenderer : IQuestionFormRenderer
    {
        private QuestionFormRenderer _basicRenderer;
        private QuestionForm _questionForm;
        private StyleSheet _styleSheet;

        private readonly TabControl _tabControl = new TabControl
        {
            AutoSize = true
        };

        public QuestionFormWithStyleRenderer(QuestionForm questionForm, StyleSheet styleSheet)
        {
            _questionForm = questionForm;
            _styleSheet = styleSheet;
            _basicRenderer = new QuestionFormRenderer(_questionForm);
        }

        public Control Render()
        {
            return _tabControl;
        }
    }
}
