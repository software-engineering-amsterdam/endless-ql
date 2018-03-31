using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Windows.Forms;
using Assignment1.Execution;
using Assignment1.Model.QL.AST;
using Assignment1.Model.QL.AST.Value;
using Assignment1.Model.QLS.AST;
using Assignment1.Rendering.Widget;

namespace Assignment1.Rendering.QLS
{
    public class QLSRenderer : IQuestionFormRenderer, IQLSASTVisitor
    {
        private readonly QLExecutor _executor;
        private readonly StyleSheet _styleSheet;
        private readonly TabControl _controlContainer = new TabControl();
        private readonly Dictionary<string, Panel> _pages = new Dictionary<string, Panel>();

        private readonly Stack<DefaultStyle> _defaultStyles = new Stack<DefaultStyle>();
        private string _currentPage;

        public QLSRenderer(QLExecutor executor, StyleSheet styleSheet)
        {
            _executor = executor;
            _styleSheet = styleSheet;
        }

        public Control Render()
        {
            _styleSheet.Accept(this);
            _controlContainer.Size = new Size(1000, 1000);
            return _controlContainer;
        }

        private void AddControlToCurrentPage(Control control)
        {
            _pages[_currentPage].Controls.Add(control);
        }

        private void SetAnswer(string questionId, IValue value)
        {
            _executor.SetAnswer(questionId, value);
            Render();
        }

        private void RenderQuestion(Question question)
        {
            AddControlToCurrentPage(QLWidgetFactory.GetWidget(
                question.Label,
                _executor.GetAnswer(question.Id),
                _executor.IsReadOnly(question.Id),
                value => SetAnswer(question.Id, value)
            ).Render());
        }

        public void Visit(StyleSheet styleSheet)
        {
            foreach (var page in styleSheet.Pages)
            {
                page.Accept(this);
            }
        }

        private void AddDefaultStyles(IEnumerable<DefaultStyle> styles)
        {
            foreach (var style in styles)
                _defaultStyles.Push(style);
        }

        private void RemoveDefaultStyles(IEnumerable<DefaultStyle> styles)
        {
            foreach (var unused in styles)
                _defaultStyles.Pop();
        }

        public void Visit(Page page)
        {
            var defaultStyles = page.Statements.OfType<DefaultStyle>().ToArray();
            AddDefaultStyles(defaultStyles);
            _currentPage = page.Id;
            if (_pages.ContainsKey(page.Id))
                _pages[page.Id].Controls.Clear();
            else
                AddPage(page);
            foreach (var statement in page.Statements)
                statement.Accept(this);
            RemoveDefaultStyles(defaultStyles);
        }

        private void AddPage(Page page)
        {
            var tabPage = new TabPage(page.Id);
            _pages.Add(page.Id, new FlowLayoutPanel
            {
                AutoSize = true,
                AutoSizeMode = AutoSizeMode.GrowAndShrink,
                FlowDirection = FlowDirection.TopDown
            });
            tabPage.Controls.Add(_pages[_currentPage]);
            _controlContainer.Controls.Add(tabPage);
        }

        public void Visit(Section section)
        {
            var defaultStyles = section.Contents.OfType<DefaultStyle>().ToArray();
            AddDefaultStyles(defaultStyles);
            AddControlToCurrentPage(new Label
            {
                Text = section.Label,
                AutoSize = true
            });
            foreach (var statement in section.Contents)
            {
                statement.Accept(this);
            }
            RemoveDefaultStyles(defaultStyles);
        }

        public void Visit(QuestionStyle questionStyle)
        {
            if (_executor.VisibleQuestions.Select(question => question.Id).Contains(questionStyle.Id))
            {
                RenderQuestion(_executor.GetQuestion(questionStyle.Id));
            }
        }

        public void Visit(DefaultStyle defaultStyle) { }
    }
}
