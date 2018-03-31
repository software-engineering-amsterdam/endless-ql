using Microsoft.VisualStudio.TestTools.UnitTesting;
using QLParser;
using QLParser.AST.QLS;

namespace QL_Parser.Tests.QLS.DefaultStyles
{
    [TestClass]
    public class DefaultStyleTests
    {
        private const string StylesheetDefaultsRaw = "stylesheet TestForm {" +
            "  page \"First page\" {" +
            "  }" +
            "  default money {" +
            "       font-size: 10" +
            "       text-size: 15" +
            "   }" +
            "}";

        private const string StyleSheetMultipleDefaultsRaw = "stylesheet TestForm {" +
            "  page \"First page\" {" +
            "  }" +
            "  default money {" +
            "       font-size: 10" +
            "       colour: #FFFFFF" +
            "   }" +
            "  default text {" +
            "       font-size: 35" +
            "       colour: #100000" +
            "       width: 150" +
            "   }" +
            "}";

        [TestMethod]
        public void StylesAtStylesheetLevelTest()
        {
            QLSNode node = QLSParserHelper.Parse(StylesheetDefaultsRaw);

            Assert.AreEqual(1, node.Children.Count);
            Assert.AreEqual(2, node.NodeStyles[0].StylingValues.Count);
        }

        [TestMethod]
        public void MultipleStylesAtStylesheetLevelTest()
        {
            QLSNode node = QLSParserHelper.Parse(StyleSheetMultipleDefaultsRaw);

            Assert.AreEqual(1, node.Children.Count);
            Assert.AreEqual(2, node.NodeStyles[0].StylingValues.Count);
            Assert.AreEqual(3, node.NodeStyles[1].StylingValues.Count);
        }
    }
}
