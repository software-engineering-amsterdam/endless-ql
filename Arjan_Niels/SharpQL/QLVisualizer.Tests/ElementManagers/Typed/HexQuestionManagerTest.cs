using Microsoft.VisualStudio.TestTools.UnitTesting;
using QLVisualizer;
using QLVisualizer.Elements.Managers.LeafTypes;
using QLVisualizer.Tests.Elements.Managers;

namespace QL_Visualizer.Tests.ElementManagers.Typed
{
    [TestClass]
    public class HexQuestionManagerTest : ElementQuestionManagerTest<HexQuestionManager, Hexadecimal>
    {
        [TestInitialize]
        public void Initialize()
        {
            Widget = new HexQuestionManager("id", "question", null, null);
        }

        public override void AssignTest()
        {
            Widget.SetAnswer("#FFFFFF");
            Assert.AreEqual(Widget.Answer.Value.ToString(), "#FFFFFF");
        }

        public override void ValueTest()
        {
            Assert.AreEqual(Widget.Answer.Value.ToInteger(), 0);
        }
    }
}
