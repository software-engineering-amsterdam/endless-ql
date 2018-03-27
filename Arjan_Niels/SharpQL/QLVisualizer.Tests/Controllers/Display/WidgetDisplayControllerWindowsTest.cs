using System.Windows.Forms;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using QLVisualizer.Controllers;
using QLVisualizer.Controllers.Display;
using QLVisualizer.Style;
using QLVisualizer.Elements.Managers.LeafTypes;
using QLVisualizer.Elements.Managers.CollectionTypes;

namespace QLVisualizer.Tests.Controllers.Display
{
    [TestClass]
    public class WidgetDisplayControllerWindowsTest : WidgetDisplayControllerTest<Control, WindowsStyleProperties>
    {
        [TestInitialize]
        public void Initialize()
        {
            _widgetStyle = new WindowsStyleProperties();

            _elementManagerDisplayContoller = new ElementManagerDisplayContollerWindows(null, 10);
            _intWidget = new IntQuestionManager("a", "q1", null, _elementManagerDisplayContoller);
            _boolWidget = new BoolQuestionManager("b", "q2", null, _elementManagerDisplayContoller);
            _stringWidget = new StringQuestionManager("c", "q3", null, _elementManagerDisplayContoller);
            _formManager = new FormManager("d", "form", _elementManagerDisplayContoller);
        }

        [TestMethod]
        public override void UpdateIntElementTest()
        {
            // Set initial value
            _intWidget.SetAnswer(10);

            Assert.AreEqual(_intWidget.Answer.Value, 10);

            _intWidget.SetAnswer(20);
            Assert.AreEqual(_intWidget.Answer.Value, 20);

            _intWidget.SetAnswer("30");
            Assert.AreEqual(_intWidget.Answer.Value, 30);

            _intWidget.SetAnswer("random string");
            Assert.AreEqual(_intWidget.Answer.Value, 30);
        }

        [TestMethod]
        public override void UpdateBoolElementTest()
        {
            // Set initial value
            _boolWidget.SetAnswer(true);
            Assert.AreEqual(_boolWidget.Answer.Value, true);   
            
            // Update value
            _boolWidget.SetAnswer(false);
            Assert.AreEqual(_boolWidget.Answer.Value, false);

            _boolWidget.SetAnswer("true");
            Assert.AreEqual(_boolWidget.Answer.Value, true);
            Assert.AreEqual(_boolWidget.Answer.IsValid, true);

            _boolWidget.SetAnswer("random string");
            Assert.AreEqual(_boolWidget.Answer.Value, true);
        }

        [TestMethod]
        public override void UpdateStringElementTest()
        {
            // Set initial value
            _stringWidget.SetAnswer("fail");

            Assert.AreEqual(_stringWidget.Answer.Value, "fail");

            // Update value
            _stringWidget.SetAnswer("pass");
            Assert.AreEqual(_stringWidget.Answer.Value, "pass");
        }
    }
}
