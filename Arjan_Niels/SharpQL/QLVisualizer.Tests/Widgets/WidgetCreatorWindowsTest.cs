using Microsoft.VisualStudio.TestTools.UnitTesting;
using QLVisualizer.Elements.Managers.LeafTypes;
using QLVisualizer.Widgets;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace QL_Visualizer.Tests.Widgets
{
    [TestClass]
    public class WidgetCreatorWindowsTest
    {
        private WidgetCreatorWindows _widgetCreatorWindows;

        [TestInitialize]
        public void Initialize()
        {
            _widgetCreatorWindows = new WidgetCreatorWindows();
        }

        [TestMethod]
        public void BoolWidgetTest()
        {
            BoolQuestionManager boolQuestionManager = new BoolQuestionManager("a", "question 1", null, null);
            Control widget = _widgetCreatorWindows.CreateWidget(boolQuestionManager, new Panel());
            Assert.IsInstanceOfType(widget.Controls[0].GetType(), typeof(CheckBox));
            Assert.AreEqual(widget.Controls[0].Text, boolQuestionManager.Text);
        }

        [TestMethod]
        public void IntWidgetTest()
        {
            IntQuestionManager intQuestionManager = new IntQuestionManager("a", "question 1", null, null);
            Control widget = _widgetCreatorWindows.CreateWidget(intQuestionManager, new Panel());
            Assert.IsInstanceOfType(widget.Controls[0].GetType(), typeof(Label));
            Assert.AreEqual(widget.Controls[0].Text, intQuestionManager.Text);
            Assert.IsInstanceOfType(widget.Controls[1].GetType(), typeof(TextBox));
        }

        [TestMethod]
        public void MoneyWidgetTest()
        {
            MoneyQuestionManager moneyQuestionManager = new MoneyQuestionManager("a", "question 1", null, null);
            Control widget = _widgetCreatorWindows.CreateWidget(moneyQuestionManager, new Panel());
            Assert.IsInstanceOfType(widget.Controls[0].GetType(), typeof(Label));
            Assert.AreEqual(widget.Controls[0].Text, moneyQuestionManager.Text);
            Assert.IsInstanceOfType(widget.Controls[1].GetType(), typeof(TextBox));
        }

        [TestMethod]
        public void StringWidgetTest()
        {
            StringQuestionManager stringQuestionManager = new StringQuestionManager("a", "question 1", null, null);
            Control widget = _widgetCreatorWindows.CreateWidget(stringQuestionManager, new Panel());
            Assert.IsInstanceOfType(widget.Controls[0].GetType(), typeof(Label));
            Assert.AreEqual(widget.Controls[0].Text, stringQuestionManager.Text);
            Assert.IsInstanceOfType(widget.Controls[1].GetType(), typeof(TextBox));
        }
    }
}
