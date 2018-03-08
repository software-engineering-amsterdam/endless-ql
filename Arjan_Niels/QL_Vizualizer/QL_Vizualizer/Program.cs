using QLVizualizer.Controllers;
using QLVizualizer.Controllers.Display;
using System;
using System.Windows.Forms;

namespace QLVizualizer
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
