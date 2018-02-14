using QL_Vizualizer.Controllers;
using QL_Vizualizer.Controllers.Display;
using QL_Vizualizer.Widgets;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace QL_Vizualizer
{
    public partial class Visualizer : Form
    {
        public Visualizer()
        {
            InitializeComponent();
            WidgetController.Initialize(new WidgetVisualizeController<Control>(new WidgetDisplayControllerWindows(10, panel1)));
            WidgetController.Instance.SetWidgets(new List<QLWidget>()
            {
                new QLWidgetInt("a", "wat is 10 + 1?", new Expression<bool>(() => {return true; }), null),
                new QLWidgetInt("b", "wat is 5 + 3?", new Expression<bool>(() => {return true; }), null),
                new QLWidgetInt("c", "som:", new Expression<bool>(() => {return true; }), new Expression<int>(() => {
                    return (WidgetController.Instance.GetWidget("a") as QLWidgetInt).AnswerValue + (WidgetController.Instance.GetWidget("b") as QLWidgetInt).AnswerValue;
                }, "a", "b"))
            });

            WidgetController.Instance.Show();
        }
    }
}
