using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using QL_Vizualizer.Controllers;
using QL_Vizualizer.Controllers.Display;
using QL_Vizualizer.Factories;
using QL_Vizualizer.Widgets.Types;

namespace QL_Visualizer.Tests.Display
{
    [TestClass]
    public abstract class WidgetDisplayControllerTest<T>
    {
        protected WidgetController _widgetController;
        protected WidgetDisplayController<T> _widgetDisplayController;
        protected ElementFactory<T> _elementFactory;

        private QLWidgetInt _intWidget;
        private QLWidgetBool _boolWidget;
        private QLWidgetString _stringWidget;

        [TestInitialize]
        public void Initialize()
        {
            _intWidget = new QLWidgetInt("a", "q1");
            _boolWidget = new QLWidgetBool("b", "q2");
            _stringWidget = new QLWidgetString("c", "q3");
        }


        [TestMethod]
        public void CreateElementTest()
        {
            //_widgetController.
        }

        [TestMethod]
        public abstract void UpdateElementTest();
    }
}
