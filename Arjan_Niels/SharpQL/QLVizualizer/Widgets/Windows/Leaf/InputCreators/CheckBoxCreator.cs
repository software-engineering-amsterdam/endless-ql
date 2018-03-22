using QLVisualizer.Elements.Managers;
using QLVisualizer.Widgets.Leaf;
using System;
using System.Windows.Forms;

namespace QLVisualizer.Widgets.Windows.Leaf.InputCreators
{
    public class CheckBoxCreator<T> : IInputCreator<Control, T>
    {
        public Control CreateInput(IStyler<Control> styler, string[] text, QuestionElementManager<T> questionElementManager)
        {
            FlowLayoutPanel holder = new FlowLayoutPanel { FlowDirection = FlowDirection.TopDown };
            CheckBox checkBox = new CheckBox { Text = text[0] };
            checkBox.CheckedChanged += (object sender, EventArgs eventArgs) => questionElementManager.SetAnswer(checkBox.Checked.ToString());
            questionElementManager.OnAnswerValueUpdate += (ElementManagerLeaf elementManagerLeaf, bool calculated) =>
            {
                if (calculated)
                {
                    bool check = false;
                    bool.TryParse(elementManagerLeaf.AnswerToString(), out check);
                    checkBox.Checked = check;

                }
            };

            holder.Controls.Add(styler.StyleElement(checkBox));

            questionElementManager.OnActiveChange += (string identifier, bool isActive) => holder.Visible = isActive;
            holder.Visible = questionElementManager.Active;

            return styler.StyleElement(holder);
        }
    }
}
