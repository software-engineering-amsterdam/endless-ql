using Microsoft.VisualStudio.TestTools.UnitTesting;
using QLVisualizer.Elements.Managers.LeafTypes;

namespace QLVisualizer.Tests.Elements.Managers.Typed
{
    [TestClass]
    public class BoolWidgetTest : QuestionWidget<BoolQuestionManager, bool>
    {    
        [TestInitialize]
        public void Initialize()
        {
            Widget = new BoolQuestionManager("id", "question", null, null);
        }

        [TestMethod]
        public override void AssignTest()
        {
            Widget.SetAnswer(true);
            Assert.IsTrue(Widget.Answer.Value);
        }

        [TestMethod]
        public override void ValueTest()
        {
            Assert.AreEqual(false, Widget.Answer.Value);
        }
    }
}
