using Microsoft.VisualStudio.TestTools.UnitTesting;
using QLParser;
using QLParser.AST.QLS;

namespace QL_Parser.Tests.QLS.DefaultStyles
{
    [TestClass]
    public class DefaultStylesInSectionTests
    {
        private const string SectionMultipleDefaultsRaw = "stylesheet TestForm {" +
            "  page \"First page\" {" +
            "       section \"First section\"{" +
            "       " +
            "           default money {" +
            "               font-size: 10" +
            "               colour: #FFFFFF" +
            "           }" +
            "           default text {" +
            "               font-size: 35" +
            "               colour: #100000" +
            "               width: 150" +
            "           }" +
            "       }" +
            "    }" +
            "}";


        private const string SectionDefaultsRaw = "stylesheet TestForm {" +
            "  page \"First page\" {" +
            "       section \"First section\"{" +
            "       " +
            "           default money {" +
            "               font-size: 10" +
            "               colour: #FFFFFF" +
            "           }" +
            "       }" +
            "    }" +
            "}";

        [TestMethod]
        public void MultipleStyleInSectionTest()
        {
            QLSNode node = QLSParserHelper.Parse(SectionMultipleDefaultsRaw);

            Assert.AreEqual(1, node.Children.Count);
            Assert.AreEqual(1, node.Children[0].Children.Count);
            Assert.AreEqual(2, node.Children[0].Children[0].NodeStyles[0].StylingValues.Count);
            Assert.AreEqual(3, node.Children[0].Children[0].NodeStyles[1].StylingValues.Count);
        }

        [TestMethod]
        public void StyleInSectionTest()
        {
            QLSNode node = QLSParserHelper.Parse(SectionDefaultsRaw);

            Assert.AreEqual(1, node.Children.Count);
            Assert.AreEqual(1, node.Children[0].Children.Count);
            Assert.AreEqual(2, node.Children[0].Children[0].NodeStyles[0].StylingValues.Count);
        }
    }
}

