using System;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using Assignment1.Model.QL;
using Assignment1.Model.QL.RenderTree;

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

        public void Visit(RenderableQuestionBool question)
        {
            _panel.Controls.Add(RenderQuestionBool(question));
        }

        public void Visit(RenderableQuestionInt question)
        {
            _panel.Controls.Add(RenderQuestionLabel(question));
            _panel.Controls.Add(RenderQuestionInt(question));
        }

        public void Visit(RenderableQuestionDate question)
        {
            _panel.Controls.Add(RenderQuestionLabel(question));
            _panel.Controls.Add(RenderQuestionDate(question));
        }

        public void Visit(RenderableQuestionDecimal question)
        {
            _panel.Controls.Add(RenderQuestionLabel(question));
            _panel.Controls.Add(RenderQuestionDecimal(question));
        }

        public void Visit(RenderableQuestionMoney question)
        {
            _panel.Controls.Add(RenderQuestionLabel(question));
            _panel.Controls.Add(RenderQuestionMoney(question));
        }

        public void Visit(RenderableQuestionString question)
        {
            _panel.Controls.Add(RenderQuestionLabel(question));
            _panel.Controls.Add(RenderQuestionString(question));
        }

        public CheckBox RenderQuestionBool(RenderableQuestionBool question)
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

        public static Label RenderQuestionLabel(RenderableQuestion question)
        {
            if (question.Condition?.Evaluate() == false) return null;
            return new Label
            {
                Text = question.Label,
                AutoSize = true
            };
        }

        public NumericUpDown RenderQuestionInt(RenderableQuestionInt question)
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

        public DateTimePicker RenderQuestionDate(RenderableQuestionDate question)
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

        public NumericUpDown RenderQuestionDecimal(RenderableQuestionDecimal question)
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

        public NumericUpDown RenderQuestionMoney(RenderableQuestionMoney question)
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

        public TextBox RenderQuestionString(RenderableQuestionString question)
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
