using Microsoft.VisualStudio.TestTools.UnitTesting;
using QLVisualizer.Elements.Managers.LeafTypes;

namespace QLVisualizer.Tests.Elements.Managers.Typed
{
    [TestClass]
    public class IntWidgetTest : QuestionWidget<IntQuestionManager, int>
    {
        [TestInitialize]
        public void Initialize()
        {
            Widget = new IntQuestionManager("id", "question", null, null);
        }

        [TestMethod]
        public override void ValueTest()
        {
            Assert.AreEqual(0, Widget.Answer.Value);
        }

        [TestMethod]
        public override void AssignTest()
        {
            Widget.SetAnswer(10);
            Assert.AreEqual(10, Widget.Answer.Value);
        }
    }
}
