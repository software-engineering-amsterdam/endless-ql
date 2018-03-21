using System;
using System.Collections.Generic;
using System.Drawing;
using System.Windows.Forms;

namespace Assignment1
{
    public partial class Form1 : Form, IMainView
    {
        public Form1()
        {
            InitializeComponent();
            var presenter = new MainPresenter(this);
            openFileButton.Click += SelectQLFile;
        }

        public event EventHandler SelectQLFile;

        public void SetFormControl(Control control)
        {
            _questionFormPanel.Controls.Clear();
            _questionFormPanel.Controls.Add(control);
        }

        private void SetMessages(string title, List<string> messages, Control titleControlTemplate, Control messageControlTemplate)
        {
            if (messages == null || messages.Count == 0)
                return;
            titleControlTemplate.Text = title;
            _messagePanel.Controls.Add(titleControlTemplate);
            foreach (string message in messages)
            {
                Label messageControl = new Label
                {
                    Font = messageControlTemplate.Font,
                    ForeColor = messageControlTemplate.ForeColor,
                    AutoSize = true
                };
                messageControl.Text = message;
                _messagePanel.Controls.Add(messageControl);
            }
        }

        public void SetErrors(List<string> errors)
        {
            var header = new Label
            {
                Width = 1000,
                Height = 30,
                TextAlign = ContentAlignment.MiddleCenter,
                Font = new Font("Arial", 12, FontStyle.Bold)
            };
            var label = new Label
            {
                Width = 1000,
                Font = new Font("Arial", 10),
                ForeColor = Color.Red
            };
            SetMessages("Provided form is invalid!", errors, header, label);
        }
        
        public void SetWarnings(List<string> warnings)
        {
            var header = new Label
            {
                AutoSize = true,
                Font = new Font("Arial", 9, FontStyle.Bold)
            };
            var label = new Label
            {
                AutoSize = true,
                Font = new Font("Arial", 8),
                ForeColor = Color.DarkOrange
            };
            SetMessages("Warning:", warnings, header, label);
        }
    }
}
