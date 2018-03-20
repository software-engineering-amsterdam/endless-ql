using Microsoft.VisualStudio.TestTools.UnitTesting;
using QLVisualizer.Controllers.Display;
using QLVisualizer.Elements.Managers.LeafTypes;
using System;

namespace QLVisualizer.Tests.Controllers.Display
{
    [TestClass]
    public abstract class WidgetDisplayControllerTest<T,Y> where Y : ICloneable
    {
        //protected WidgetController _widgetController;
        protected WidgetDisplayController<T, Y> _widgetDisplayController;
        //protected ElementFactory<T> _elementFactory;

        protected IntQuestionManager _intWidget;
        protected BoolQuestionManager _boolWidget;
        protected StringQuestionManager _stringWidget;

        protected Y _widgetStyle;

        [TestMethod]
        public void ShowTest()
        {
            // when int widget is added, the element must be there
            // TODO: re implement this test
            /*_widgetDisplayController.ShowWidget(_intWidget, _widgetStyle);
            Assert.IsTrue(_widgetDisplayController.ElementsIndex.ContainsKey(_intWidget.Identifier));

            // when string widget is added, the element must be there
            _widgetDisplayController.ShowWidget(_stringWidget, _widgetStyle);
            Assert.IsTrue(_widgetDisplayController.ElementsIndex.ContainsKey(_stringWidget.Identifier));

            // when bool widget is added, the element must be there
            _widgetDisplayController.ShowWidget(_boolWidget, _widgetStyle);
            Assert.IsTrue(_widgetDisplayController.ElementsIndex.ContainsKey(_boolWidget.Identifier));

            // there must now be a total of three elements
            Assert.AreEqual(3, _widgetDisplayController.ElementsIndex.Count);*/
        }

        [TestMethod]
        public abstract void UpdateIntElementTest();

        [TestMethod]
        public abstract void UpdateBoolElementTest();

        [TestMethod]
        public abstract void UpdateStringElementTest();
    }
}
