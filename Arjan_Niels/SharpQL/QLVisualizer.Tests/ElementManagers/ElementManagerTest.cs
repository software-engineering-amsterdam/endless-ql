using Microsoft.VisualStudio.TestTools.UnitTesting;
using QLVisualizer.ElementManagers;

namespace QLVisualizer.Tests.ElementManagers
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
