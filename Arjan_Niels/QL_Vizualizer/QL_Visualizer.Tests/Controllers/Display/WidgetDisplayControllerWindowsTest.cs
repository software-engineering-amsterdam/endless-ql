using System.Windows.Forms;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using QL_Vizualizer.Controllers;
using QL_Vizualizer.Controllers.Display;
using QL_Vizualizer.Style;
using QL_Vizualizer.Widgets.Types;

namespace QL_Visualizer.Tests.Controllers.Display
{
    [TestClass]
    public class WidgetDisplayControllerWindowsTest : WidgetDisplayControllerTest<Control, WindowsStyleProperties>
    {
        [TestInitialize]
        public void Initialize()
        {
            _widgetStyle = new WindowsStyleProperties();

            _widgetDisplayController = new WidgetDisplayControllerWindows(10);
            _intWidget = new QLWidgetInt("a", "q1");
            _boolWidget = new QLWidgetBool("b", "q2");
            _stringWidget = new QLWidgetString("c", "q3");
        }

        [TestMethod]
        public override void UpdateIntElementTest()
        {
            // Set initial value
            _intWidget.SetAnswer(10);

            // Create element
            _widgetDisplayController.ShowWidget(_intWidget, _widgetStyle);

            // Update value
            _intWidget.SetAnswer(20);

            // Element value does not equal origional value
            Assert.AreNotEqual(_widgetDisplayController.ElementIndex[_intWidget.Identifyer], 10);
        }

        [TestMethod]
        public override void UpdateBoolElementTest()
        {
            // Set initial value
            _boolWidget.SetAnswer(true);

            // Create element
            _widgetDisplayController.ShowWidget(_boolWidget, _widgetStyle);
            
            // Update value
            _boolWidget.SetAnswer(false);

            // Element value does not equal origional value
            Assert.AreNotEqual(_widgetDisplayController.ElementIndex[_boolWidget.Identifyer], true);
        }

        [TestMethod]
        public override void UpdateStringElementTest()
        {
            // Set initial value
            _stringWidget.SetAnswer("fail");

            // Create element
            _widgetDisplayController.ShowWidget(_stringWidget, _widgetStyle);

            // Update value
            _stringWidget.SetAnswer("pass");

            // Element value does not equal origional value
            Assert.AreNotEqual(_widgetDisplayController.ElementIndex[_stringWidget.Identifyer], "fail");
        }
    }
}
