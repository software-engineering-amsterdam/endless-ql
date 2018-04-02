using QLVisualizer.Elements.Managers;
using QLVisualizer.Widgets.Leaf;
using System;
using System.Windows.Forms;

namespace QLVisualizer.Widgets.Windows.Leaf.InputCreators
{
    public class SpinnerCreator<T> : IInputCreator<Control, T>
    {
        public Control CreateInput(IStyler<Control> styler, string[] text, QuestionElementManager<T> questionElementManager)
        {
            NumericUpDown spinner = new NumericUpDown();
            Label question = new Label { Text = text[0] };
            FlowLayoutPanel holder = new FlowLayoutPanel { FlowDirection = FlowDirection.TopDown };

            spinner.ValueChanged += (object sender, EventArgs eventArgs) => questionElementManager.SetAnswer(spinner.Value.ToString());
            questionElementManager.OnAnswerValueUpdate += (ElementManagerLeaf elementManagerLeaf, bool calculated) => { if (calculated) { spinner.Value = decimal.Parse(questionElementManager.AnswerToString()); } };
            questionElementManager.OnActiveChange += (string identifier, bool isActive) => holder.Visible = isActive;
            holder.Visible = questionElementManager.Active;

            holder.Controls.Add(question);
            holder.Controls.Add(spinner);
            return styler.StyleElement(holder);
        }
    }
}
