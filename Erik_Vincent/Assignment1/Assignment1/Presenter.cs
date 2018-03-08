using System;
using System.Collections.Generic;
using System.Drawing;
using System.Windows.Forms;
using Assignment1.Model;

namespace Assignment1
{
    internal class Presenter : IQuestionVisitor
    {
        public readonly Panel Panel = new FlowLayoutPanel
        {
            AutoSize = true,
            AutoSizeMode = AutoSizeMode.GrowAndShrink,
            FlowDirection = FlowDirection.TopDown
        };

        private readonly QuestionForm _form;

        public Presenter()
        {
            QLListener listener = QuestionForm.ParseString(System.IO.File.ReadAllText("test.txt"));
            if (listener.FormHasErrors)
            {
                ReportFormErrors(listener.Errors);
            }
            else
            {
                _form = listener.Form;
                UpdateControls();
            }
        }

        private void ReportFormErrors(List<string> errors)
        {
            var header = new Label
            {
                Text = "Provided form is invalid!",
                Width = 1000,
                Height = 30,
                TextAlign = ContentAlignment.MiddleCenter,
                Font = new Font("Arial", 12, FontStyle.Bold)
            };
            Panel.Controls.Add(header);
            foreach (string error in errors)
            {
                var label = new Label
                {
                    Text = error,
                    Width = 1000,
                    Font = new Font("Arial", 10),
                    ForeColor = Color.Red
                };
                Panel.Controls.Add(label);
            }
        }

        private void UpdateControls()
        {
            Panel.SuspendLayout();
            Panel.Controls.Clear();
            foreach (var question in _form.Questions)
            {
                question.Accept(this);
            }
            Panel.ResumeLayout();
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
            Panel.Controls.Add(checkbox);
        }

        public void Visit(QuestionInt question)
        {
            if (question.Condition?.Evaluate() == false) return;
            Panel.Controls.Add(new Label
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
            Panel.Controls.Add(numericUpDown);
        }

        public void Visit(QuestionDate question)
        {
            if (question.Condition?.Evaluate() == false) return;
            Panel.Controls.Add(new Label
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
            Panel.Controls.Add(dateTimePicker);
        }

        public void Visit(QuestionDecimal question)
        {
            if (question.Condition?.Evaluate() == false) return;
            Panel.Controls.Add(new Label
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
            Panel.Controls.Add(numericUpDown);
        }

        public void Visit(QuestionMoney question)
        {
            if (question.Condition?.Evaluate() == false) return;
            Panel.Controls.Add(new Label
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
            Panel.Controls.Add(numericUpDown);
        }

        public void Visit(QuestionString question)
        {
            if (question.Condition?.Evaluate() == false) return;
            Panel.Controls.Add(new Label
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
            Panel.Controls.Add(textBox);
        }
    }
}
