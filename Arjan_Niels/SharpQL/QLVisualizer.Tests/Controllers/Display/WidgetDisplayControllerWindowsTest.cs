using System.Windows.Forms;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using QLVisualizer.Controllers;
using QLVisualizer.Controllers.Display;
using QLVisualizer.Style;
using QLVisualizer.ElementManagers.LeafTypes;

namespace QLVisualizer.Tests.Controllers.Display
{
    [TestClass]
    public class WidgetDisplayControllerWindowsTest : WidgetDisplayControllerTest<Control, WindowsStyleProperties>
    {
        [TestInitialize]
        public void Initialize()
        {
            _widgetStyle = new WindowsStyleProperties();

            _widgetDisplayController = new ElementManagerDisplayContollerWindows(null, 10);
            _intWidget = new IntQuestionManager("a", "q1", null, _widgetDisplayController);
            _boolWidget = new BoolQuestionManager("b", "q2", null, _widgetDisplayController);
            _stringWidget = new StringQuestionManager("c", "q3", null, _widgetDisplayController);
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
            Assert.AreNotEqual(_widgetDisplayController.ElementIndex[_intWidget.Identifier], 10);
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
            Assert.AreNotEqual(_widgetDisplayController.ElementIndex[_boolWidget.Identifier], true);
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
            Assert.AreNotEqual(_widgetDisplayController.ElementIndex[_stringWidget.Identifier], "fail");
        }
    }
}
