using Microsoft.VisualStudio.TestTools.UnitTesting;
using QLVizualizer.ElementManagers;

namespace QLVisualizer.Tests.ElementManagers
{
    public  abstract class QuestionWidget<T, Y> : ElementManagerTest<T> where T : QuestionElementManager<Y>
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
