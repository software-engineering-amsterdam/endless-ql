using Microsoft.VisualStudio.TestTools.UnitTesting;
using QLVisualizer.ElementManagers.Types;

namespace QLVisualizer.Tests.ElementManagers.Typed
{
    [TestClass]
    public class IntWidgetTest : QuestionWidget<IntElementManager, int>
    {
        [TestInitialize]
        public void Initialize()
        {
            Widget = new IntElementManager("id", "question", null, null);
        }

        [TestMethod]
        public override void ValueTest()
        {
            Assert.AreEqual(0, Widget.AnswerValue);
        }

        [TestMethod]
        public override void AssignTest()
        {
            Widget.SetAnswer(10);
            Assert.AreEqual(10, Widget.AnswerValue);
        }
    }
}
