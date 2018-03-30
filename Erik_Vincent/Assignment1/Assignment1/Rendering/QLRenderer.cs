using System.Windows.Forms;
using Assignment1.Execution;
using Assignment1.Model.QL.AST;
using Assignment1.Model.QL.AST.Value;
using Assignment1.Rendering.Widget;

namespace Assignment1.Rendering
{
    public class QLRenderer : IQuestionFormRenderer
    {
        private readonly QLExecutor _executor;
        private readonly Panel _controlContainer = new FlowLayoutPanel
        {
            AutoSize = true,
            AutoSizeMode = AutoSizeMode.GrowAndShrink,
            FlowDirection = FlowDirection.TopDown
        };

        public QLRenderer(QLExecutor executor)
        {
            _executor = executor;
        }

        public Control Render()
        {
            foreach (var question in _executor.VisibleQuestions)
            {
                RenderQuestion(question);
            }
            return _controlContainer;
        }

        private void AddControl(Control control) => _controlContainer.Controls.Add(control);

        private void ClearControls() => _controlContainer.Controls.Clear();

        private void SetAnswer(string questionId, IValue value)
        {
            _executor.SetAnswer(questionId, value);
            ClearControls();
            Render();
        }

        private void RenderQuestion(Question question)
        {
            AddControl(QLWidgetFactory.GetWidget(
                    question.Label,
                    _executor.GetAnswer(question.Id),
                    _executor.IsReadOnly(question.Id),
                    value => SetAnswer(question.Id, value)
                ).Render());
        }
    }
}
