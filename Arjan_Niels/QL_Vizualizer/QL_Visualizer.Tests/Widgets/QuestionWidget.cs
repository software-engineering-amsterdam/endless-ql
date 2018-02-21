using Microsoft.VisualStudio.TestTools.UnitTesting;
using QL_Vizualizer.Widgets;

namespace QL_Visualizer.Tests.Widgets
{
    public  abstract class QuestionWidget<T, Y> : WidgetTest<T> where T : QLQuestionWidget<Y>
    {
        [TestMethod]
        public void EditableTest()
        {
            Assert.IsTrue(Widget.Editable);
        }

        [TestMethod]
        public abstract void ValueTest();

        [TestMethod]
        public abstract void AssignTest();
    }
}
