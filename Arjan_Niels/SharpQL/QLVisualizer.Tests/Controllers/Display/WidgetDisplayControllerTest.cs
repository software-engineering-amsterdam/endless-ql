using Microsoft.VisualStudio.TestTools.UnitTesting;
using QLVisualizer.Controllers.Display;
using QLVisualizer.Elements.Managers.CollectionTypes;
using QLVisualizer.Elements.Managers.LeafTypes;
using System;

namespace QLVisualizer.Tests.Controllers.Display
{
    [TestClass]
    public abstract class WidgetDisplayControllerTest<T>
    {
        //protected WidgetController _widgetController;
        protected WidgetDisplayController<T> _elementManagerDisplayContoller;
        //protected ElementFactory<T> _elementFactory;

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
