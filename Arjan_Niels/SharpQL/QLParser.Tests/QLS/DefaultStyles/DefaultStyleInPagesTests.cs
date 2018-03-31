using Microsoft.VisualStudio.TestTools.UnitTesting;
using QLParser;
using QLParser.AST.QLS;

namespace QL_Parser.Tests.QLS.DefaultStyles
{
    [TestClass]
    public class DefaultStyleInPagesTests
    {
        private const string PageMultipleDefaultsRaw = "stylesheet TestForm {" +
            "  page \"First page\" {" +
            "   default money {" +
            "       font-size: 10" +
            "       colour: #FFFFFF" +
            "    }" +
            "   default text {" +
            "       font-size: 35" +
            "       colour: #100000" +
            "       width: 150" +
            "    }" +
            "  }" +
            "}";

        private const string PageStylesDefaultsRaw = "stylesheet TestForm {" +
            "  page \"First page\" {" +
            "   default money {" +
            "       font-size: 10" +
            "       colour: #FFFFFF" +
            "    }" +
            "  }" +
            "}";

        [TestMethod]
        public void MultipleStylesAtPageLevelTest()
        {
            QLSNode node = QLSParserHelper.Parse(PageMultipleDefaultsRaw);

            Assert.AreEqual(1, node.Children.Count);
            Assert.AreEqual(2, node.Children[0].NodeStyles[0].StylingValues.Count);
            Assert.AreEqual(3, node.Children[0].NodeStyles[1].StylingValues.Count);
        }

        [TestMethod]
        public void StylesAtPageLevelTest()
        {
            QLSNode node = QLSParserHelper.Parse(PageStylesDefaultsRaw);

            Assert.AreEqual(1, node.Children.Count);
            Assert.AreEqual(2, node.Children[0].NodeStyles[0].StylingValues.Count);
        }
    }
}