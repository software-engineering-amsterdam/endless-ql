using QL_Vizualizer.Controllers;
using QL_Vizualizer.Controllers.Display;
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

            // Initialize widget controller
            WidgetController widgetController = new WidgetVisualizeController<Control>(new WidgetDisplayControllerWindows(10, panel1));

            // Create widgets
            widgetController.SetWidgets(new List<QLWidget>()
            {
                new QLWidgetInt("a", "wat is 10 + 1?", widgetController, new Expression<bool>(() => {return true; }), null),
                new QLWidgetInt("b", "wat is 5 + 3?", widgetController, new Expression<bool>(() => {return true; }), null),
                new QLWidgetInt("c", "som:", widgetController, new Expression<bool>(() => {return true; }), new Expression<int>(() => {
                    return (widgetController.GetWidget("a") as QLWidgetInt).AnswerValue + (widgetController.GetWidget("b") as QLWidgetInt).AnswerValue;
                }, "a", "b")),
                new QLWidgetBool("d","This statement is False", widgetController, new Expression<bool>(() => {return true; }), null)

            });

            // Display widgets
            widgetController.Show();
        }
    }
}
