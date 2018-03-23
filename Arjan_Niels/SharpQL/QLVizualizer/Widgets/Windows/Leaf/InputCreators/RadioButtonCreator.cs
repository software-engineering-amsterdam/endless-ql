using QLVisualizer.Elements.Managers;
using QLVisualizer.Widgets.Leaf;
using System;
using System.Linq;
using System.Windows.Forms;

namespace QLVisualizer.Widgets.Windows.Leaf.InputCreators
{
    public class RadioButtonCreator<T> : IInputCreator<Control, T>
    {
        public Control CreateInput(IStyler<Control> styler, string[] text, QuestionElementManager<T> questionElementManager)
        {
            string title = text[0];
            string[] answerOptions = AllButFirst(text);

            FlowLayoutPanel holder = new FlowLayoutPanel { FlowDirection = FlowDirection.TopDown };
            Label titleLabel = new Label { Text = title };
            Control styledTitleLabel = styler.StyleElement(titleLabel);

            holder.Controls.Add(styledTitleLabel);

            Control radioButtons = CreateRadioButtons(styler, answerOptions, questionElementManager);
            radioButtons = styler.StyleElement(radioButtons);
            holder.Controls.Add(radioButtons);

            return styler.StyleElement(holder);
            
        }

        private Control CreateRadioButtons(IStyler<Control> styler, string[] text, QuestionElementManager<T> questionElementManager)
        {
            FlowLayoutPanel holder = new FlowLayoutPanel { FlowDirection = FlowDirection.TopDown };
            foreach(string option in text)
            {
                RadioButton radioButton = new RadioButton { Text = option };
                radioButton.CheckedChanged += (object sender, EventArgs eventArgs) => questionElementManager.SetAnswer(radioButton.Text);
                holder.Controls.Add(styler.StyleElement(radioButton));
            }
            return holder;
        }

        private string[] AllButFirst(string[] array)
        {
            return Enumerable.Select(Enumerable.Range(1, array.Length - 1), i => array[i]).ToArray();
        }
    }
}
