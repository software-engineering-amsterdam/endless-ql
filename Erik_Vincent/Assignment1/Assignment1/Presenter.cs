using System;
using System.Collections.Generic;
using System.Windows.Forms;
using Assignment1.Model;

namespace Assignment1
{
    internal class Presenter : IContentVisitor
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
            _form = QuestionForm.ParseString(System.IO.File.ReadAllText("test.txt"));
            UpdateControls();
        }

        private void UpdateControls()
        {
            Panel.SuspendLayout();
            Panel.Controls.Clear();
            foreach (var content in _form.Content)
            {
                content.Accept(this);
            }
            Panel.ResumeLayout();
        }

        public void Visit(QuestionBool question)
        {
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
                ReadOnly = question.Computed
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
                ReadOnly = question.Computed
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
                ReadOnly = question.Computed
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

        public void Visit(IfStatement ifStatement)
        {
            if (!ifStatement.Expression.Evaluate()) return;
            foreach (var content in ifStatement.ThenContent)
            {
                content.Accept(this);
            }
        }

        public void Visit(IfElseStatement ifElseStatement)
        {
            var contents = ifElseStatement.Expression.Evaluate()
                ? ifElseStatement.ThenContent
                : ifElseStatement.ElseContent;
            foreach (var content in contents)
            {
                content.Accept(this);
            }
        }
    }
}
