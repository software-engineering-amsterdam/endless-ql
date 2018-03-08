using Microsoft.VisualStudio.TestTools.UnitTesting;
using QL_Vizualizer.Controllers.Display;
using QL_Vizualizer.Style;
using QL_Vizualizer.Widgets.Types;
using System;

namespace QL_Visualizer.Tests.Controllers.Display
{
    [TestClass]
    public abstract class WidgetDisplayControllerTest<T,Y> where Y : ICloneable
    {
        //protected WidgetController _widgetController;
        protected WidgetDisplayController<T, Y> _widgetDisplayController;
        //protected ElementFactory<T> _elementFactory;

        protected QLWidgetInt _intWidget;
        protected QLWidgetBool _boolWidget;
        protected QLWidgetString _stringWidget;

        protected Y _widgetStyle;

        [TestMethod]
        public void ShowTest()
        {
            // when int widget is added, the element must be there
            _widgetDisplayController.ShowWidget(_intWidget, _widgetStyle);
            Assert.IsTrue(_widgetDisplayController.ElementIndex.ContainsKey(_intWidget.Identifyer));

            // when string widget is added, the element must be there
            _widgetDisplayController.ShowWidget(_stringWidget, _widgetStyle);
            Assert.IsTrue(_widgetDisplayController.ElementIndex.ContainsKey(_stringWidget.Identifyer));

            // when bool widget is added, the element must be there
            _widgetDisplayController.ShowWidget(_boolWidget, _widgetStyle);
            Assert.IsTrue(_widgetDisplayController.ElementIndex.ContainsKey(_boolWidget.Identifyer));

            // there must now be a total of three elements
            Assert.AreEqual(3, _widgetDisplayController.ElementIndex.Count);
        }

        [TestMethod]
        public abstract void UpdateIntElementTest();

        [TestMethod]
        public abstract void UpdateBoolElementTest();

        [TestMethod]
        public abstract void UpdateStringElementTest();
    }
}
