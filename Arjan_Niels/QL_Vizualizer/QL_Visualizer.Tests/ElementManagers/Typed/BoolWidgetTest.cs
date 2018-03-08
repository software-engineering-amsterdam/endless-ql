using Microsoft.VisualStudio.TestTools.UnitTesting;
using QL_Vizualizer.ElementManagers.Types;

namespace QL_Visualizer.Tests.ElementManagers.Typed
{
    [TestClass]
    public class BoolWidgetTest : QuestionWidget<BoolElementManager, bool>
    {    
        [TestInitialize]
        public void Initialize()
        {
            Widget = new BoolElementManager("id", "question", null, null);
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
