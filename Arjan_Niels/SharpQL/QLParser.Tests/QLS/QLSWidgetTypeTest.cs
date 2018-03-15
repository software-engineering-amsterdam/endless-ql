using Microsoft.VisualStudio.TestTools.UnitTesting;
using QLParser;
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
            Assert.AreEqual(WidgetType.SPINNER, questionNode.NodeStyle.WidgetSpecification.WidgetType);
            Assert.AreEqual(0, questionNode.NodeStyle.WidgetSpecification.WidgetTypeArguments.Count);
        }

        [TestMethod]
        public void WidgetStyleArgumentsTest()
        {
            QLSNode qls = QLSParserHelper.Parse(SimpleStyleWithWidgetTypeWithArguments);

            var questionNode = qls.Children[0].Children[0].Children[0];
            Assert.AreEqual(WidgetType.RADIO, questionNode.NodeStyle.WidgetSpecification.WidgetType);
            Assert.AreEqual(3, questionNode.NodeStyle.WidgetSpecification.WidgetTypeArguments.Count);
            Assert.AreEqual("Yes", questionNode.NodeStyle.WidgetSpecification.WidgetTypeArguments[0]);
            Assert.AreEqual("No", questionNode.NodeStyle.WidgetSpecification.WidgetTypeArguments[1]);
            Assert.AreEqual("Maybe", questionNode.NodeStyle.WidgetSpecification.WidgetTypeArguments[2]);
        }
    }
}
