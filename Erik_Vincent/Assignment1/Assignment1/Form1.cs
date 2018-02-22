using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using Antlr4.Runtime;
using Antlr4.Runtime.Tree;

namespace Assignment1
{
    public partial class Form1 : Form
    {
        private readonly Controller _controller = new Controller();
        private readonly FlowLayoutPanel _panel = new FlowLayoutPanel() { FlowDirection = FlowDirection.TopDown, AutoScroll = true, WrapContents = false, Dock = DockStyle.Fill };

        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            var forms = _controller.ParseString(System.IO.File.ReadAllText("test.txt"));
            foreach (var content in forms[0].Content)
            {
                _panel.Controls.Add(content.CreateControl());
            }
            Controls.Add(_panel);
        }
    }
}
