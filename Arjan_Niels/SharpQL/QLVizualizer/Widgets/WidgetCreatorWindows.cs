using System;
using System.Drawing;
using System.Windows.Forms;
using QLVisualizer.Elements.Managers;
using QLVisualizer.Elements.Managers.CollectionTypes;
using QLVisualizer.Elements.Managers.LeafTypes;

namespace QLVisualizer.Widgets
{
    public class WidgetCreatorWindows : WidgetCreator<Control>
    {
        private Control CreateWidgetContainer(ElementManagerCollection elementManagerCollection, Control holder, string titleText)
        {
            // Set distance between objects
            int margin = 10;
            int pos = 0 + margin;

            // Create title
            Label title = new Label()
            {
                Text = titleText,
                Location = new Point(0, pos),
                Width = holder.Width
            };

            // Add title
            holder.Controls.Add(title);
            pos += title.Height + margin;

            // Itetate through children
            foreach (ElementManager child in elementManagerCollection.Children)
            {
                // Create holder for child
                Control childHolder = new Panel()
                {
                    Location = new Point(0, pos),
                    Width = holder.Width
                };

                // Create actual widget for child
                childHolder = CreateWidget(child, childHolder);

                // Add child to holder
                holder.Controls.Add(childHolder);
                pos += childHolder.Height + margin;
            }

            // Set height of holder
            holder.Height = pos;

            elementManagerCollection.OnActiveChange += delegate (string identifier, bool isActive) { holder.Visible = isActive; };

            // Return filled holder
            return holder;
        }

        private Control CreateWidgetLeaf(ElementManagerLeaf elementManager, Control holder, string question, params Control[] controls)
        {
            int margin = 10;
            int pos = margin;

            if(!string.IsNullOrEmpty(question))
            {
                Label questionLabel = new Label()
                {
                    Text = question,
                    Location = new Point(0, pos),
                    Width = holder.Width
                };
                holder.Controls.Add(questionLabel);
                pos += questionLabel.Height + margin;
            }

            foreach (Control control in controls)
            {
                control.Location = new Point(0, pos);
                control.Width = holder.Width;
                holder.Controls.Add(control);
                pos += control.Height + margin;
            }

            elementManager.OnActiveChange += delegate (string identifier, bool isActive) { holder.Visible = isActive; };

            holder.Height = pos;
            return holder;
        }

        private Control CreateTextBoxWidget<T>(QuestionElementManager<T> questionElementManager, Control holder)
        {
            TextBox questionInput = new TextBox();
            if (questionElementManager.Editable)
                questionInput.TextChanged += delegate (object sender, EventArgs eventArgs) { questionElementManager.ParseInput(questionInput.Text); };
            else
                questionElementManager.OnAnswerValueUpdate += delegate (QuestionElementValue<T> answer, bool calculated) { if (calculated) questionInput.Text = answer.Value.ToString(); };
            
            return CreateWidgetLeaf(questionElementManager, holder, questionElementManager.Text, questionInput);
        }

        protected override Control CreateWidget(FormManager form, Control holder)
        {
            return CreateWidgetContainer(form, holder, string.Format("Form: {0}", form.Text));
        }

        protected override Control CreateWidget(PageManager page, Control holder)
        {
            return CreateWidgetContainer(page, holder, string.Format("Page: {0}", page.Text));
        }

        protected override Control CreateWidget(SectionManager section, Control holder)
        {
            return CreateWidgetContainer(section, holder, string.Format("Section: {0}", section.Text));
        }

        protected override Control CreateWidget(BoolQuestionManager boolQuestion, Control holder)
        {
            // Create input
            CheckBox question = new CheckBox() { Text = boolQuestion.Text };
            if (boolQuestion.Editable)
                question.CheckedChanged += delegate (object sender, EventArgs eventArgs) { boolQuestion.SetAnswer(question.Checked); };
            else
                boolQuestion.OnAnswerValueUpdate += delegate (QuestionElementValue<bool> questionElementValue, bool calculated) {
                    if (calculated) question.Checked = questionElementValue.Value;
                };

            return CreateWidgetLeaf(boolQuestion, holder, "", question);
        }

        protected override Control CreateWidget(IntQuestionManager intQuestion, Control holder)
        {
            return CreateTextBoxWidget(intQuestion, holder);
        }

        protected override Control CreateWidget(MoneyQuestionManager moneyQuestion, Control holder)
        {
            return CreateTextBoxWidget(moneyQuestion, holder);
        }

        protected override Control CreateWidget(StringQuestionManager stringQuestion, Control holder)
        {
            return CreateTextBoxWidget(stringQuestion, holder);
        }
    }
}
