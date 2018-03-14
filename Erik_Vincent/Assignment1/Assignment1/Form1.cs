using System;
using System.Collections.Generic;
using System.Drawing;
using System.IO;
using System.Windows.Forms;
using Assignment1.Export;
using Assignment1.Rendering;

namespace Assignment1
{
    public partial class Form1 : Form
    {
        private FlowLayoutPanel _mainPanel;

        public Form1()
        {
            InitializeComponent();
            _mainPanel = new FlowLayoutPanel
            {
                AutoSize = true,
                AutoSizeMode = AutoSizeMode.GrowAndShrink,
                FlowDirection = FlowDirection.TopDown
            };
            Controls.Add(_mainPanel);
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            ParseFile("test.txt");
        }

        private void ParseFile(string fileLocation)
        {
            _mainPanel.Controls.Clear();
            try
            {
                var form = QLListener.ParseString(File.ReadAllText(fileLocation));
                _mainPanel.Controls.Add(RenderFileSelector());
                IQuestionFormRenderer renderer = new QuestionFormRenderer(form);
                _mainPanel.Controls.Add(renderer.Render());
                FormExporter exporter = new FormExporter(form);
                _mainPanel.Controls.Add(exporter.Render());
            }
            catch (QLParseException exception)
            {
                ReportFormErrors(exception.Exceptions);
            }
        }

        private ToolStrip RenderFileSelector()
        {
            ToolStrip fileSelectorPanel = new ToolStrip();
            ToolStripButton toolStripButton = new ToolStripButton("Open file", null, FileSelectorClicked);
            fileSelectorPanel.Items.Add(toolStripButton);
            return fileSelectorPanel;
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
            _mainPanel.Controls.Add(header);
            foreach (string error in errors)
            {
                var label = new Label
                {
                    Text = error,
                    Width = 1000,
                    Font = new Font("Arial", 10),
                    ForeColor = Color.Red
                };
                _mainPanel.Controls.Add(label);
            }
        }

        private void FileSelectorClicked(object sender, EventArgs eventArgs)
        {
            OpenFileDialog fileDialog = new OpenFileDialog();
            fileDialog.InitialDirectory = Path.GetDirectoryName(Directory.GetCurrentDirectory());
            if (fileDialog.ShowDialog() == DialogResult.OK)
            {
                ParseFile(fileDialog.FileName);
            }
        }
    }
}
