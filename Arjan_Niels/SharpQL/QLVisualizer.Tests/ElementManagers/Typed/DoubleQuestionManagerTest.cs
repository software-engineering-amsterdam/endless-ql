using Microsoft.VisualStudio.TestTools.UnitTesting;
using QLVisualizer.Elements.Managers.LeafTypes;
using QLVisualizer.Tests.Elements.Managers;

namespace QL_Visualizer.Tests.ElementManagers.Typed
{
    [TestClass]
    public class DoubleQuestionManagerTest : ElementQuestionManagerTest<DoubleQuestionManager, double>
    {

        [TestInitialize]
        public void Initialize()
        {
            Widget = new DoubleQuestionManager("id", "question", null, null);
        }

        [TestMethod]
        public override void AssignTest()
        {
            Widget.SetAnswer(10);
            Assert.AreEqual(Widget.Answer.Value, 10);
        }

        [TestMethod]
        public override void ValueTest()
        {
            Assert.AreEqual(Widget.Answer.Value, 0);
        }
    }
}
