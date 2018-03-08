using Microsoft.VisualStudio.TestTools.UnitTesting;
using QLVizualizer.ElementManagers.Types;

namespace QLVisualizer.Tests.ElementManagers.Typed
{
    [TestClass]
    public class StringWidgetTest : QuestionWidget<StringElementManager, string>
    {
        [TestInitialize]
        public void Initialize()
        {
            Widget = new StringElementManager("id", "question");
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
            Assert.AreEqual(null, Widget.AnswerValue);
        }
    }
}
