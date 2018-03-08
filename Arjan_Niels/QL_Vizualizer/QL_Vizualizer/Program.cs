using QL_Vizualizer.Controllers;
using QL_Vizualizer.Controllers.Display;
using QL_Vizualizer.Style;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace QL_Vizualizer
{
    static class Program
    {
        /// <summary>
        /// The main entry point for the application.
        /// </summary>
        [STAThread]
        static void Main()
        {
            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(false);

            // Initialize widget controller
            ElementManagerController widgetController = new ElementManagerDisplayContollerWindows(10);
            widgetController.ShowView();
        }
    }
}
