using QL_Parser;
using QL_Parser.AST.Nodes;
using QL_Vizualizer.Controllers;
using QL_Vizualizer.Controllers.Display;
using QL_Vizualizer.Style;
using QL_Vizualizer.Widgets;
using QL_Vizualizer.Widgets.Types;
using System.Collections.Generic;
using System.Windows.Forms;

namespace QL_Vizualizer
{
    public partial class Visualizer : Form
    {
        public Visualizer()
        {
            InitializeComponent();
        }

        private void parseButtonClick(object sender, System.EventArgs e)
        {
            CreateDummyForm();
            FormNode node = QLParserHelper.Parse(textBox1.Text);
        }

        private void CreateDummyForm()
        {
            // Initialize widget controller
            WidgetController widgetController = new WidgetVisualizeController<Control, WindowsStyleProperties>();
            widgetController.SetDisplayController(new WidgetDisplayControllerWindows(10, panel1, widgetController));

            // Create widgets
            widgetController.SetWidgets(new List<QLWidget>()
            {
                new QLWidgetInt("a", "wat is 10 + 1?"),
                new QLWidgetInt("b", "wat is 5 + 3?"),
                new QLWidgetInt("c", "som:", null, new Expression<int>(() => {
                    return (widgetController.GetWidget("a") as QLWidgetInt).AnswerValue + (widgetController.GetWidget("b") as QLWidgetInt).AnswerValue;
                }, "a", "b")),
                new QLWidgetBool("d", "This statement is False")

            });

            // Display widgets
            widgetController.Show();
        }
    }
}
