using Microsoft.VisualStudio.TestTools.UnitTesting;
using QL_Vizualizer.Widgets;
using QL_Vizualizer.Widgets.Types;
using System.Linq.Expressions;

namespace QL_Visualizer.Tests
{
    [TestClass]
    public abstract class WidgetTest<T> where T : QLWidget
    {
        protected T Widget;

        [TestMethod]
        public void ActiveTest()
        {
            Assert.IsTrue(Widget.Active);
        }
    }
}
