using System;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using Assignment1.Model.QL;
using Assignment1.Model.QL.AST.Value;
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
        private SymbolTable _table;
        private ExpressionEvaluator _evaluator;

        public QuestionFormRenderer(QuestionForm form, SymbolTable table)
        {
            _form = form;
            _table = table;
            _evaluator = new ExpressionEvaluator(_table);
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
            if (!question.IsVisible(_evaluator)) return null;
            string value = _evaluator.GetValueAsString(_table.GetExpression(question.Id));
            var checkbox = new CheckBox
            {
                Text = question.Label,
                AutoSize = true,
                Checked = Boolean.Parse(value),
                Enabled = !question.Computed
            };
            if (!question.Computed)
            {
                checkbox.CheckedChanged += (sender, e) =>
                {
                    _table.SetExpression(question.Id, new QLBoolean(((CheckBox)sender).Checked));
                    UpdateControls();
                };
            }
            return checkbox;
        }

        public Label RenderQuestionLabel(RenderableQuestion question)
        {
            if (!question.IsVisible(_evaluator)) return null;
            return new Label
            {
                Text = question.Label,
                AutoSize = true
            };
        }

        public NumericUpDown RenderQuestionInt(RenderableQuestionInt question)
        {
            if (!question.IsVisible(_evaluator)) return null;
            IValue value = _evaluator.EvaluateExpression(_table.GetExpression(question.Id));
            string stringValue = value.Type == Model.QL.AST.Type.Undefined ? "0" : _evaluator.GetValueAsString(value);
            var numericUpDown = new NumericUpDown
            {
                Minimum = int.MinValue,
                Maximum = int.MaxValue,
                DecimalPlaces = 0,
                Value = int.Parse(stringValue),
                Enabled = !question.Computed
            };
            if (!question.Computed)
            {
                numericUpDown.ValueChanged += (sender, e) =>
                {
                    _table.SetExpression(question.Id, new QLInteger((int)((NumericUpDown)sender).Value));
                    UpdateControls();
                };
            }
            return numericUpDown;
        }

        public DateTimePicker RenderQuestionDate(RenderableQuestionDate question)
        {
            if (!question.IsVisible(_evaluator)) return null;
            IValue value = _evaluator.EvaluateExpression(_table.GetExpression(question.Id));
            string stringValue = value.Type == Model.QL.AST.Type.Undefined ? "" : _evaluator.GetValueAsString(value);
            var dateTimePicker = new DateTimePicker
            {
                MinDate = DateTime.MinValue,
                MaxDate = DateTime.MaxValue,
                Value = DateTime.Parse(stringValue),
                Enabled = !question.Computed
            };
            if (!question.Computed)
            {
                dateTimePicker.ValueChanged += (sender, e) =>
                {
                    _table.SetExpression(question.Id, new QLDate(((DateTimePicker)sender).Value));
                    UpdateControls();
                };
            }

            return dateTimePicker;
        }

        public NumericUpDown RenderQuestionDecimal(RenderableQuestionDecimal question)
        {
            if (!question.IsVisible(_evaluator)) return null;
            IValue value = _evaluator.EvaluateExpression(_table.GetExpression(question.Id));
            string stringValue = value.Type == Model.QL.AST.Type.Undefined ? "0" : _evaluator.GetValueAsString(value);
            var numericUpDown = new NumericUpDown
            {
                Minimum = decimal.MinValue,
                Maximum = decimal.MaxValue,
                DecimalPlaces = 4,
                Value = Decimal.Parse(stringValue),
                Enabled = !question.Computed
            };
            if (!question.Computed)
            {
                numericUpDown.ValueChanged += (sender, e) =>
                {
                    _table.SetExpression(question.Id, new QLDecimal(((NumericUpDown)sender).Value));
                    UpdateControls();
                };
            }

            return numericUpDown;
        }

        public NumericUpDown RenderQuestionMoney(RenderableQuestionMoney question)
        {
            if (!question.IsVisible(_evaluator)) return null;
            IValue value = _evaluator.EvaluateExpression(_table.GetExpression(question.Id));
            string stringValue = value.Type == Model.QL.AST.Type.Undefined ? "0" : _evaluator.GetValueAsString(value);
            var numericUpDown = new NumericUpDown
            {
                Minimum = decimal.MinValue,
                Maximum = decimal.MaxValue,
                DecimalPlaces = 2,
                Value = Decimal.Parse(stringValue),
                Enabled = !question.Computed
            };
            if (!question.Computed)
            {
                numericUpDown.ValueChanged += (sender, e) =>
                {
                    _table.SetExpression(question.Id, new QLMoney(((NumericUpDown)sender).Value));
                    UpdateControls();
                };
            }

            return numericUpDown;
        }

        public TextBox RenderQuestionString(RenderableQuestionString question)
        {
            if (!question.IsVisible(_evaluator)) return null;
            IValue value = _evaluator.EvaluateExpression(_table.GetExpression(question.Id));
            string stringValue = value.Type == Model.QL.AST.Type.Undefined ? "" : _evaluator.GetValueAsString(value);
            var textBox = new TextBox
            {
                Text = stringValue,
                ReadOnly = question.Computed
            };
            if (!question.Computed)
            {
                textBox.TextChanged += (sender, e) =>
                {
                    _table.SetExpression(question.Id, new QLString(((TextBox)sender).Text));
                    UpdateControls();
                };
            }

            return textBox;
        }
    }
}
