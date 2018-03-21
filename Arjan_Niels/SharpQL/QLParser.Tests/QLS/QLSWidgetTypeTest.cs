using Microsoft.VisualStudio.TestTools.UnitTesting;
using QLParser;
using QLParser.AST.Nodes;
using QLParser.AST.QLS;
using QLParser.AST.QLS.Enums;

namespace QL_Parser.Tests.QLS
{
    [TestClass]
    public class QLSWidgetTypeTest
    {
        private const string SimpleStyle = "stylesheet TestForm {" +
            "   page \"FirstPage\" {" +
            "      section \"SectionOne\" {" +
            "           question hasSoldHouse" +
            "       }" +
            "   }" +
            "}";

        private const string SimpleStyleWithWidgetType = "stylesheet TestForm {" +
            "   page \"FirstPage\" {" +
            "      section \"SectionOne\" {" +
            "           question hasSoldHouse widget spinner" +
            "       }" +
            "   }" +
            "}";

        private const string SimpleStyleWithWidgetTypeWithArguments = "stylesheet TestForm {" +
            "   page \"FirstPage\" {" +
            "      section \"SectionOne\" {" +
            "           question hasSoldHouse widget radio(\"Yes\", \"No\", \"Maybe\")" +
            "       }" +
            "   }" +
            "}";
        private const string SimpleStyleWithDefaults = "stylesheet TestForm {" +
            "   page \"FirstPage\" {" +
            "      section \"SectionOne\" {" +
            "           question hasSoldHouse widget radio(\"Yes\", \"No\", \"Maybe\")" +
            "           default money {" +
            "               width: 100" +
            "               fontSize: 12.5" +
            "           }" +
            "       }" +
            "   }" +
            "}";

        private const string SimpleStyleWithMultipleDefaults = "stylesheet TestForm {" +
    "   page \"FirstPage\" {" +
    "      section \"SectionOne\" {" +
    "           question hasSoldHouse widget radio(\"Yes\", \"No\", \"Maybe\")" +
    "           default money {" +
    "               width: 100" +
    "               fontSize: 12.5" +
    "           }" +
    "       }" +
    "   }" +
    "}";

        [TestMethod]
        public void StylesheetNameTest()
        {
            QLSNode qls = QLSParserHelper.Parse(SimpleStyle);
            Assert.AreEqual("TestForm", qls.ID);
            Assert.AreEqual(QLSNodeType.Stylesheet, qls.NodeType);
        }

        [TestMethod]
        public void PageNameTest()
        {
            QLSNode qls = QLSParserHelper.Parse(SimpleStyle);
            Assert.AreEqual("FirstPage", qls.Children[0].ID);
            Assert.AreEqual(QLSNodeType.Page, qls.Children[0].NodeType);
        }

        [TestMethod]
        public void SectionNameTest()
        {
            QLSNode qls = QLSParserHelper.Parse(SimpleStyle);
            Assert.AreEqual("SectionOne", qls.Children[0].Children[0].ID);
            Assert.AreEqual(QLSNodeType.Section, qls.Children[0].Children[0].NodeType);
        }

        [TestMethod]
        public void QuestionIdentifierTest()
        {
            QLSNode qls = QLSParserHelper.Parse(SimpleStyle);
            Assert.AreEqual("hasSoldHouse", qls.Children[0].Children[0].Children[0].ID);
            Assert.AreEqual(QLSNodeType.Question, qls.Children[0].Children[0].Children[0].NodeType);
        }

        [TestMethod]
        public void WidgetStyleTest()
        {
            QLSNode qls = QLSParserHelper.Parse(SimpleStyleWithWidgetType);

            var questionNode = qls.Children[0].Children[0].Children[0];
            Assert.AreEqual(WidgetType.SPINNER, questionNode.NodeStyles[0].WidgetSpecification.WidgetType);
            Assert.AreEqual(0, questionNode.NodeStyles[0].WidgetSpecification.WidgetTypeArguments.Count);
        }

        [TestMethod]
        public void WidgetStyleArgumentsTest()
        {
            QLSNode qls = QLSParserHelper.Parse(SimpleStyleWithWidgetTypeWithArguments);

            var questionNode = qls.Children[0].Children[0].Children[0];
            Assert.AreEqual(WidgetType.RADIO, questionNode.NodeStyles[0].WidgetSpecification.WidgetType);
            Assert.AreEqual(3, questionNode.NodeStyles[0].WidgetSpecification.WidgetTypeArguments.Count);
            Assert.AreEqual("Yes", questionNode.NodeStyles[0].WidgetSpecification.WidgetTypeArguments[0]);
            Assert.AreEqual("No", questionNode.NodeStyles[0].WidgetSpecification.WidgetTypeArguments[1]);
            Assert.AreEqual("Maybe", questionNode.NodeStyles[0].WidgetSpecification.WidgetTypeArguments[2]);
        }

        [TestMethod]
        public void DefaultStylesTest()
        {
            QLSNode qls = QLSParserHelper.Parse(SimpleStyleWithDefaults);

            var styles = qls.Children[0].NodeStyles;
            Assert.AreEqual("width", styles[0].StylingValues[0].StyleProperty);
            Assert.AreEqual(QValueType.INTEGER, styles[0].StylingValues[0].QValueType);

            Assert.AreEqual("fontSize", styles[0].StylingValues[1].StyleProperty);
            Assert.AreEqual(QValueType.DOUBLE, styles[0].StylingValues[1].QValueType);
        }

        [TestMethod]
        public void MultipleDefaultStylesTest()
        {
            QLSNode qls = QLSParserHelper.Parse(SimpleStyleWithMultipleDefaults);

            var styles = qls.Children[0].NodeStyles;
            Assert.AreEqual(2, styles[0].StylingValues.Count);
        }
    }
}