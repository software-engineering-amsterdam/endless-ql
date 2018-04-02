using QLVisualizer.Elements.Managers;
using QLVisualizer.Widgets.Leaf;
using System;
using System.Windows.Forms;

namespace QLVisualizer.Widgets.Windows.Leaf.InputCreators
{
    public class TextBoxCreator<T> : IInputCreator<Control, T>
    {
        public Control CreateInput(IStyler<Control> styler, string[] text, QuestionElementManager<T> questionElementManager)
        {
            // Create holder
            FlowLayoutPanel holder = new FlowLayoutPanel { FlowDirection = FlowDirection.TopDown };
            holder.Controls.Add(styler.StyleElement(new Label { Text = text[0] }));

            // Create and style textbox
            Control textbox = CreateTextBox(questionElementManager);
            textbox.Text = questionElementManager.AnswerToString();
            textbox = styler.StyleElement(textbox);
            holder.Controls.Add(textbox);

            // Add events on holder
            questionElementManager.OnActiveChange += (string identifier, bool isActive) => holder.Visible = isActive;
            holder.Visible = questionElementManager.Active;

            return styler.StyleElement(holder);
        }

        private Control CreateTextBox(QuestionElementManager<T> questionElementManager)
        {
            TextBox textBox = new TextBox();
            textBox.TextChanged += (object sender, EventArgs eventArgs) => questionElementManager.SetAnswer(textBox.Text);
            questionElementManager.OnAnswerValueUpdate += (ElementManagerLeaf elementmanagerLeaf, bool calculated) => { if (calculated) { textBox.Text = elementmanagerLeaf.AnswerToString(); } };
            return textBox;
        }
    }
}
