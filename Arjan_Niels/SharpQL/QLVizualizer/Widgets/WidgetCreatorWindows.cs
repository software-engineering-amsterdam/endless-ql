using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Windows.Forms;
using QLVisualizer.Elements.Managers;
using QLVisualizer.Elements.Managers.CollectionTypes;
using QLVisualizer.Elements.Managers.LeafTypes;

namespace QLVisualizer.Widgets
{
    public class WidgetCreatorWindows : WidgetCreator<Control>
    {
        private List<PageManager> _pageList;

        private Control CreateWidgetContainer(ElementManagerCollection elementManagerCollection, Control holder, string titleText)
        {
            // Set distance between objects
            int margin = 10;
            int pos = 0 + margin;

            // Create title
            if (!string.IsNullOrEmpty(titleText))
            {
                Label title = new Label()
                {
                    Text = titleText,
                    Location = new Point(0, pos),
                    Width = holder.Width
                };

                // Add title
                holder.Controls.Add(title);
                pos += title.Height + margin;
            }

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

            if (!string.IsNullOrEmpty(question))
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
                control.Enabled = elementManager.Editable;
            }

            elementManager.OnActiveChange += delegate (string identifier, bool isActive) { holder.Visible = isActive; };
            holder.Visible = elementManager.Active;

            holder.Height = pos;
            return holder;
        }

        private Control CreateTextBoxWidget<T>(QuestionElementManager<T> questionElementManager, Control holder)
        {
            TextBox questionInput = new TextBox();

            questionInput.TextChanged += delegate (object sender, EventArgs eventArgs) { questionElementManager.SetAnswer(questionInput.Text); };

            questionElementManager.OnAnswerValueUpdate += delegate (ElementManagerLeaf elementManagerLeaf, bool calculated)
            {
                if (calculated)
                    questionInput.Text = elementManagerLeaf.AnswerToString();
            };

            return CreateWidgetLeaf(questionElementManager, holder, questionElementManager.Text, questionInput);
        }

        protected override Control CreateWidget(FormManager form, Control holder)
        {
            _pageList = new List<PageManager>();
            int margin = 10;
            int pos = margin;
            Panel result = new Panel();
            Control mainContol = CreateWidgetContainer(form, new Panel { Size = holder.Size - new Size(0, 40) }, string.Empty);
            Control[] formControls = new Control[]
            {
                new Label() { Text = string.Format("Form: {0}", form.Text) },
                CreatePagePanel(holder.Width),
                mainContol
            };

            foreach (Control c in formControls)
            {
                c.Location = new Point(10, pos);
                result.Controls.Add(c);
                pos += c.Height + margin;
            }

            result.Height = holder.Height;
            return result;
        }

        protected override Control CreateEmpty()
        {
            return new Panel { Size = new Size(0, 0) };
        }

        private Panel CreatePagePanel(int width)
        {
            int margin = 5;
            int pos = 0;

            // TODO: FIX NOT DISPLAYING PAGENAME
            //Button previous = new Button() { Text = "<" };
            //Button next = new Button() { Text = ">" };
            ComboBox selector = new ComboBox() { Width = 150, FormattingEnabled = true, Name="page selector", TabIndex = 0 };
            selector.Items.AddRange(_pageList.Select(o => o.Text).ToArray());

            // Create listeners
            selector.SelectedIndexChanged += delegate (object sender, EventArgs eventArgs)
            {
                _pageList[selector.SelectedIndex].Activate();
                //selector.Text = selector.SelectedText;
                //previous.Enabled = selector.SelectedIndex > 0;
                //next.Enabled = selector.SelectedIndex > selector.Items.Count;
            };

            //previous.Click += (object sender, EventArgs eventArgs) => selector.SelectedIndex--;
            //next.Click += (object sender, EventArgs eventArgs) => selector.SelectedIndex++;

            // Create panel
            Control[] contents = new Control[] { /*previous, */selector/*, next*/ };
            Panel result = new Panel() { Size = new Size(contents.Select(o => o.Width).Sum() + margin * contents.Length, contents.Select(o => o.Height).Max()) };
            foreach(Control content in contents)
            {
                content.Location = new Point(pos, 0);
                result.Controls.Add(content);
                pos += margin + content.Width;
            }

            return result;
        }

        protected override void RegisterPageTab(PageManager page)
        {
            _pageList.Add(page);
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
                boolQuestion.OnTypedAnswerValueUpdate += delegate (QuestionElementValue<bool> answer, bool calculated)
                {
                    if (calculated) question.Checked = answer.Value;
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
