using Microsoft.VisualStudio.TestTools.UnitTesting;
using QL_Vizualizer.Widgets.Types;

namespace QL_Visualizer.Tests.Widgets.Typed
{
    [TestClass]
    public class BoolWidgetTest : QuestionWidget<QLWidgetBool, bool>
    {    
        [TestInitialize]
        public void Initialize()
        {
            Widget = new QLWidgetBool("id", "question", null, null);
        }

        [TestMethod]
        public override void AssignTest()
        {
            Widget.SetAnswer(true);
            Assert.IsTrue(Widget.AnswerValue);
        }

        [TestMethod]
        public override void ValueTest()
        {
            Assert.AreEqual(false, Widget.AnswerValue);
        }
    }
}
