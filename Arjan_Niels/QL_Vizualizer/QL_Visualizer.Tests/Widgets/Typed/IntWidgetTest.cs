using Microsoft.VisualStudio.TestTools.UnitTesting;
using QL_Vizualizer.Widgets.Types;
using System;
using System.Collections.Generic;
using System.Text;

namespace QL_Visualizer.Tests.Widgets.Typed
{
    [TestClass]
    public class IntWidgetTest : QuestionWidget<QLWidgetInt, int>
    {
        [TestInitialize]
        public void Initialize()
        {
            Widget = new QLWidgetInt("id", "question", null, null);
        }

        [TestMethod]
        public override void ValueTest()
        {
            Assert.AreEqual(0, Widget.AnswerValue);
        }

        [TestMethod]
        public override void AssignTest()
        {
            Widget.SetAnswer(10);
            Assert.AreEqual(10, Widget.AnswerValue);
        }
    }
}
