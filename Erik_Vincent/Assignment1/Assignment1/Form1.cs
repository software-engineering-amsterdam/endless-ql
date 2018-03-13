using System;
using System.Collections.Generic;
using System.Drawing;
using System.Windows.Forms;
using Assignment1.Rendering;

namespace Assignment1
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            var listener = QLListener.ParseString(System.IO.File.ReadAllText("test.txt"));
            if (listener.FormHasErrors)
            {
                ReportFormErrors(listener.Errors);
            }
            else
            {
                IQuestionFormRenderer renderer = new QuestionFormRenderer(listener.Form);
                Controls.Add(renderer.Render());
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
            Controls.Add(header);
            foreach (string error in errors)
            {
                var label = new Label
                {
                    Text = error,
                    Width = 1000,
                    Font = new Font("Arial", 10),
                    ForeColor = Color.Red
                };
                Controls.Add(label);
            }
        }
    }
}
