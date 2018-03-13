using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using Assignment1.Model;

namespace Assignment1.Rendering
{
    internal class QuestionFormRenderer : IQuestionFormRenderer, IQuestionVisitor
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
            _panel.Controls.Add(RenderQuestionBool(question));
        }

        public void Visit(QuestionInt question)
        {
            _panel.Controls.Add(RenderQuestionLabel(question));
            _panel.Controls.Add(RenderQuestionInt(question));
        }

        public void Visit(QuestionDate question)
        {
            _panel.Controls.Add(RenderQuestionLabel(question));
            _panel.Controls.Add(RenderQuestionDate(question));
        }

        public void Visit(QuestionDecimal question)
        {
            _panel.Controls.Add(RenderQuestionLabel(question));
            _panel.Controls.Add(RenderQuestionDecimal(question));
        }

        public void Visit(QuestionMoney question)
        {
            _panel.Controls.Add(RenderQuestionLabel(question));
            _panel.Controls.Add(RenderQuestionMoney(question));
        }

        public void Visit(QuestionString question)
        {
            _panel.Controls.Add(RenderQuestionLabel(question));
            _panel.Controls.Add(RenderQuestionString(question));
        }

        public CheckBox RenderQuestionBool(QuestionBool question)
        {
            if (question.Condition?.Evaluate() == false) return null;
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
            return checkbox;
        }

        public static Label RenderQuestionLabel(Question question)
        {
            if (question.Condition?.Evaluate() == false) return null;
            return new Label
            {
                Text = question.Label,
                AutoSize = true
            };
        }

        public NumericUpDown RenderQuestionInt(QuestionInt question)
        {
            if (question.Condition?.Evaluate() == false) return null;
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
            return numericUpDown;
        }

        public DateTimePicker RenderQuestionDate(QuestionDate question)
        {
            if (question.Condition?.Evaluate() == false) return null;
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

            return dateTimePicker;
        }

        public NumericUpDown RenderQuestionDecimal(QuestionDecimal question)
        {
            if (question.Condition?.Evaluate() == false) return null;
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

            return numericUpDown;
        }

        public NumericUpDown RenderQuestionMoney(QuestionMoney question)
        {
            if (question.Condition?.Evaluate() == false) return null;
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

            return numericUpDown;
        }

        public TextBox RenderQuestionString(QuestionString question)
        {
            if (question.Condition?.Evaluate() == false) return null;
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

            return textBox;
        }
    }
}
