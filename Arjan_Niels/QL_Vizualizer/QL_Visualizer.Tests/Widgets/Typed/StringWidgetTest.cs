using Microsoft.VisualStudio.TestTools.UnitTesting;
using QL_Vizualizer.Widgets.Types;

namespace QL_Visualizer.Tests.Widgets.Typed
{
    [TestClass]
    public class StringWidgetTest : QuestionWidget<QLWidgetString, string>
    {
        [TestInitialize]
        public void Initialize()
        {
            Widget = new QLWidgetString("id", "question");
        }

        [TestMethod]
        public override void AssignTest()
        {
            Widget.SetAnswer("unittest");
            Assert.AreEqual("unittest", Widget.AnswerValue);
        }

        [TestMethod]
        public override void ValueTest()
        {
            Assert.AreEqual("", Widget.AnswerValue);
        }
    }
}
