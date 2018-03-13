using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using Assignment1.Model;

namespace Assignment1.Rendering
{
    class QuestionFormRenderer : IQuestionFormRenderer, IQuestionVisitor
    {
        private readonly Panel _panel = new FlowLayoutPanel
        {
            AutoSize = true,
            AutoSizeMode = AutoSizeMode.GrowAndShrink,
            FlowDirection = FlowDirection.TopDown
        };

        private readonly QuestionForm _form;

        public QuestionFormRenderer(QuestionForm form)
        {
            _form = form;
        }

        public Control Render()
        {
            UpdateControls();
            return _panel;
        }

        private void UpdateControls()
        {
            _panel.SuspendLayout();
            _panel.Controls.Clear();
            foreach (var question in _form.Questions)
            {
                question.Accept(this);
            }
            _panel.ResumeLayout();
        }

        public void Visit(QuestionBool question)
        {
            if (question.Condition?.Evaluate() == false) return;
            var checkbox = new CheckBox
            {
                Text = question.Label,
                AutoSize = true,
                Checked = question.Value,
                Enabled = !question.Computed
            };
            if (!question.Computed)
            {
                checkbox.CheckedChanged += (sender, e) =>
                {
                    question.Value = ((CheckBox)sender).Checked;
                    UpdateControls();
                };
            }
            _panel.Controls.Add(checkbox);
        }

        public void Visit(QuestionInt question)
        {
            if (question.Condition?.Evaluate() == false) return;
            _panel.Controls.Add(new Label
            {
                Text = question.Label,
                AutoSize = true
            });
            var numericUpDown = new NumericUpDown
            {
                Minimum = int.MinValue,
                Maximum = int.MaxValue,
                DecimalPlaces = 0,
                Value = question.Value,
                Enabled = !question.Computed
            };
            if (!question.Computed)
            {
                numericUpDown.ValueChanged += (sender, e) =>
                {
                    question.Value = (int)((NumericUpDown)sender).Value;
                    UpdateControls();
                };
            }
            _panel.Controls.Add(numericUpDown);
        }

        public void Visit(QuestionDate question)
        {
            if (question.Condition?.Evaluate() == false) return;
            _panel.Controls.Add(new Label
            {
                Text = question.Label,
                AutoSize = true
            });
            var dateTimePicker = new DateTimePicker
            {
                MinDate = DateTime.MinValue,
                MaxDate = DateTime.MaxValue,
                Value = question.Value,
                Enabled = !question.Computed
            };
            if (!question.Computed)
            {
                dateTimePicker.ValueChanged += (sender, e) =>
                {
                    question.Value = ((DateTimePicker)sender).Value;
                    UpdateControls();
                };
            }
            _panel.Controls.Add(dateTimePicker);
        }

        public void Visit(QuestionDecimal question)
        {
            if (question.Condition?.Evaluate() == false) return;
            _panel.Controls.Add(new Label
            {
                Text = question.Label,
                AutoSize = true
            });
            var numericUpDown = new NumericUpDown
            {
                Minimum = decimal.MinValue,
                Maximum = decimal.MaxValue,
                DecimalPlaces = 4,
                Value = question.Value,
                Enabled = !question.Computed
            };
            if (!question.Computed)
            {
                numericUpDown.ValueChanged += (sender, e) =>
                {
                    question.Value = ((NumericUpDown)sender).Value;
                    UpdateControls();
                };
            }
            _panel.Controls.Add(numericUpDown);
        }

        public void Visit(QuestionMoney question)
        {
            if (question.Condition?.Evaluate() == false) return;
            _panel.Controls.Add(new Label
            {
                Text = question.Label,
                AutoSize = true
            });
            var numericUpDown = new NumericUpDown
            {
                Minimum = decimal.MinValue,
                Maximum = decimal.MaxValue,
                DecimalPlaces = 2,
                Value = question.Value,
                Enabled = !question.Computed
            };
            if (!question.Computed)
            {
                numericUpDown.ValueChanged += (sender, e) =>
                {
                    question.Value = ((NumericUpDown)sender).Value;
                    UpdateControls();
                };
            }
            _panel.Controls.Add(numericUpDown);
        }

        public void Visit(QuestionString question)
        {
            if (question.Condition?.Evaluate() == false) return;
            _panel.Controls.Add(new Label
            {
                Text = question.Label,
                AutoSize = true
            });
            var textBox = new TextBox
            {
                Text = question.Value,
                ReadOnly = question.Computed
            };
            if (!question.Computed)
            {
                textBox.TextChanged += (sender, e) =>
                {
                    question.Value = ((TextBox)sender).Text;
                    UpdateControls();
                };
            }
            _panel.Controls.Add(textBox);
        }
    }
}
