using QLVisualizer.Elements.Managers;
using QLVisualizer.Widgets.Leaf;
using System;
using System.Drawing;
using System.Windows.Forms;

namespace QLVisualizer.Widgets.Windows.Leaf.InputCreators
{
    public class ColorPickerCreator<T> : IInputCreator<Control, T>
    {
        private ColorDialog _colorDialog;

        public ColorPickerCreator()
        {
            _colorDialog = new ColorDialog();
        }

        public Control CreateInput(IStyler<Control> styler, string[] text, QuestionElementManager<T> questionElementManager)
        {
            Button colorPickerButton = new Button { Text = text[0] };
            UpdateColor(questionElementManager.AnswerToString());

            colorPickerButton.Click += (object sender, EventArgs evenArgs) => ColorPickerButton_Click(questionElementManager);
            questionElementManager.OnAnswerValueUpdate += AnwerUpdate;
            questionElementManager.OnActiveChange += (string identifier, bool isActive) => colorPickerButton.Visible = isActive;
            colorPickerButton.Visible = questionElementManager.Active;

            return styler.StyleElement(colorPickerButton);
        }

        private void AnwerUpdate(ElementManagerLeaf elementManagerLeaf, bool calculated)
        {
            if (calculated)
                UpdateColor(elementManagerLeaf.AnswerToString());
        }

        private void ColorPickerButton_Click(QuestionElementManager<T> questionElementManager)
        {
            if (_colorDialog.ShowDialog() == DialogResult.OK)
            {
                Color dialogResult = _colorDialog.Color;
                string answer = ColorToHex(dialogResult);
                questionElementManager.SetAnswer(answer);
            }
        }

        private void UpdateColor(string answerValue)
        {
            _colorDialog.Color = new Hexadecimal(answerValue).ToColor();

        }

        private string ColorToHex(Color color)
        {
            return "#" + color.R.ToString("X2") + color.G.ToString("X2") + color.B.ToString("X2");
        }
    }
}
