using System;
using System.Collections.Generic;
using System.Windows.Forms;
using Assignment1.Model;

namespace Assignment1
{
    internal class Presenter
    {
        private List<QuestionForm> _forms = new List<QuestionForm>();
        private readonly FlowLayoutPanel _panel = new FlowLayoutPanel { FlowDirection = FlowDirection.TopDown, AutoScroll = true, WrapContents = false, Dock = DockStyle.Fill };
        private readonly Dictionary<object, Content> _controlContents = new Dictionary<object, Content>();
        private readonly Dictionary<Content, CheckBox> _checkBoxes = new Dictionary<Content, CheckBox>();
        private readonly Dictionary<Content, TextBox> _textBoxes = new Dictionary<Content, TextBox>();
        private readonly Dictionary<Content, Panel> _panels = new Dictionary<Content, Panel>();

        public Control GetControls()
        {
            _forms = QuestionForm.ParseString(System.IO.File.ReadAllText("test.txt"));
            foreach (dynamic content in _forms[0].Content)
            {
                _panel.Controls.Add(CreateControl(content));
            }
            return _panel;
        }

        private Control CreateControl(QuestionBool question)
        {
            var result = new CheckBox
            {
                Text = question.Label,
                AutoSize = true,
                Checked = question.Value,
                Enabled = !question.Computed
            };
            result.CheckedChanged += UpdateControls;
            _controlContents.Add(result, question);
            _checkBoxes.Add(question, result);
            return result;
        }

        private void UpdateControl(QuestionBool question)
        {
            _checkBoxes[question].Checked = question.Value;
        }

        private void UpdateInModel(QuestionBool question)
        {
            var checkBox = _checkBoxes[question];
            question.Value = checkBox.Checked;
        }

        private Control CreateControl(QuestionMoney question)
        {
            var result = new FlowLayoutPanel
            {
                AutoSize = true,
                AutoSizeMode = AutoSizeMode.GrowAndShrink,
                FlowDirection = FlowDirection.TopDown

            };
            result.Controls.Add(new Label
            {
                Text = question.Label,
                AutoSize = true
            });
            var textBox = (new TextBox()
            {
                Text = question.Value.ToString(),
                Enabled = !question.Computed
            });
            textBox.TextChanged += UpdateControls;
            result.Controls.Add(textBox);
            _controlContents.Add(textBox, question);
            _textBoxes.Add(question, textBox);
            return result;
        }

        private void UpdateControl(QuestionMoney question)
        {
            _textBoxes[question].Text = question.Value.ToString();
        }

        private void UpdateInModel(QuestionMoney question)
        {
            var textBox = _textBoxes[question];
            try
            {
                question.Value = decimal.Parse(textBox.Text);
            }
            catch (Exception e)
            {
                //TODO: Handle exception
            }
        }

        private Control CreateControl(IfStatement ifStatement)
        {
            var result = new FlowLayoutPanel
            {
                FlowDirection = FlowDirection.TopDown,
                AutoSize = true
            };
            foreach (dynamic content in ifStatement.ThenContent)
            {
                result.Controls.Add(CreateControl(content));
            }
            result.Visible = ifStatement.Expression.Evaluate();
            _controlContents.Add(result, ifStatement);
            _panels.Add(ifStatement, result);
            return result;
        }

        private void UpdateControl(IfStatement ifStatement)
        {
            _panels[ifStatement].Visible = ifStatement.Expression.Evaluate();
            foreach (dynamic content in ifStatement.ThenContent)
            {
                UpdateControl(content);
            }
        }

        private void UpdateControls(dynamic sender, System.EventArgs e)
        {
            UpdateInModel(_controlContents[sender]);
            foreach (dynamic content in _forms[0].Content)
            {
                UpdateControl(content);
            }
        }
    }
}
