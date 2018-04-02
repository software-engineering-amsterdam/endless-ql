using Microsoft.VisualStudio.TestTools.UnitTesting;
using QLVisualizer.Controllers.Display;
using QLVisualizer.Elements.Managers.CollectionTypes;
using QLVisualizer.Elements.Managers.LeafTypes;

namespace QLVisualizer.Tests.Controllers.Display
{
    [TestClass]
    public abstract class ElementManagerDisplayControllerTest<T>
    {
        protected WidgetDisplayController<T> _elementManagerDisplayContoller;

        protected IntQuestionManager _intWidget;
        protected BoolQuestionManager _boolWidget;
        protected StringQuestionManager _stringWidget;
        protected FormManager _formManager;

        [TestMethod]
        public abstract void UpdateIntElementTest();

        [TestMethod]
        public abstract void UpdateBoolElementTest();

        [TestMethod]
        public abstract void UpdateStringElementTest();
    }
}
