using System.Windows.Forms;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using QL_Vizualizer.Controllers;
using QL_Vizualizer.Controllers.Display;
using QL_Vizualizer.Factories;
using QL_Vizualizer.Widgets.Types;

namespace QL_Visualizer.Tests.Elements
{
    [TestClass]
    public class ControlFactoryTest : IElementFactoryTest
    {
        private ControlFactory _controlFactory;
        private WidgetController _widgetController;

        private QLWidgetInt _intWidget;
        private QLWidgetBool _boolWidget;
        private QLWidgetString _stringWidget;

        [TestInitialize]
        public void Initialize()
        {
            Control mainControl = new Panel();
            _widgetController = new WidgetVisualizeController<Control>();
            _widgetController.SetDisplayController(new WidgetDisplayControllerWindows(10f, mainControl, _widgetController));

            _controlFactory = new ControlFactory(_widgetController);
            _intWidget = new QLWidgetInt("a", "q1");
            _boolWidget = new QLWidgetBool("b", "q2");
            _stringWidget = new QLWidgetString("c", "q3");
        }

        #region Create
        [TestMethod]
        public void CreateBoolElementTest()
        {
            Control c = _controlFactory.CreateElement(_boolWidget);

            // Should be one item in the control
            Assert.AreEqual(1, c.Controls.Count);

            // Item shoud be a textbox
            Assert.IsInstanceOfType(c.Controls[0], typeof(CheckBox));

            // Texts should match
            Assert.AreEqual(c.Controls[0].Text, _boolWidget.Text);
        }

        [TestMethod]
        public void CreateIntElementTest()
        {
            Control c = _controlFactory.CreateElement(_intWidget);

            // Should be two items in the control
            Assert.AreEqual(2, c.Controls.Count);

            // A Label
            Assert.IsInstanceOfType(c.Controls[0], typeof(Label));

            // And a TextBox
            Assert.IsInstanceOfType(c.Controls[1], typeof(TextBox));

            // Texts should match
            Assert.AreEqual(c.Controls[0].Text, _intWidget.Text);
        }

        [TestMethod]
        public void CreateStringElementTest()
        {
            Control c = _controlFactory.CreateElement(_stringWidget);

            // Should be two items in the control
            Assert.AreEqual(2, c.Controls.Count);

            // A Label
            Assert.IsInstanceOfType(c.Controls[0], typeof(Label));

            // And a TextBox
            Assert.IsInstanceOfType(c.Controls[1], typeof(TextBox));

            // Texts should match
            Assert.AreEqual(c.Controls[0].Text, _stringWidget.Text);
        }
        #endregion

        #region Update
        [TestMethod]
        public void UpdateBoolElementTest()
        {
            Control c = _controlFactory.CreateElement(_boolWidget);

            // Control should contain a checkbox at 0
            Assert.IsInstanceOfType(c.Controls[0], typeof(CheckBox));

            // Check the checkbox
            ((CheckBox)c.Controls[0]).Checked = true;

            // Answer must now be updated
            Assert.IsTrue(_boolWidget.AnswerValue);
        }

        [TestMethod]
        public void UpdateIntElementTest()
        {
            Control c = _controlFactory.CreateElement(_intWidget);

            // Control should contain a checkbox at 0
            Assert.IsInstanceOfType(c.Controls[1], typeof(TextBox));

            // Check the checkbox
            ((TextBox)c.Controls[1]).Text = 100.ToString();

            // Answer must now be updated
            Assert.AreEqual(100, _intWidget.AnswerValue);
        }

        [TestMethod]
        public void UpdateStringElementTest()
        {
            Control c = _controlFactory.CreateElement(_stringWidget);

            // Control should contain a checkbox at 0
            Assert.IsInstanceOfType(c.Controls[1], typeof(TextBox));

            // Check the checkbox
            ((TextBox)c.Controls[1]).Text = "unittest";

            // Answer must now be updated
            Assert.AreEqual("unittest", _stringWidget.AnswerValue);
        }
        #endregion
    }
}
