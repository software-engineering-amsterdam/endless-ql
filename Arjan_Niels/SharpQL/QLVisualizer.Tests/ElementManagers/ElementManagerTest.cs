using Microsoft.VisualStudio.TestTools.UnitTesting;
using QLVisualizer.Elements.Managers;

namespace QLVisualizer.Tests.Elements.Managers
{
    [TestClass]
    public abstract class ElementManagerTest<T> where T : ElementManagerLeaf
    {
        protected T Widget;

        [TestMethod]
        public void ActiveTest()
        {
            Assert.IsTrue(Widget.Active);
        }
    }
}
