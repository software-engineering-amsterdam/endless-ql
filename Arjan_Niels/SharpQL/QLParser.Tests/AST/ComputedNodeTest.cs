using Microsoft.VisualStudio.TestTools.UnitTesting;
using QLParser.AST.Nodes;
using System.Linq;

namespace QLParser.Tests.AST
{
    [TestClass]
    public class ComputedNodeTest : QLTest
    {
        private readonly string _simpleComputedForm = "form SimpleForm {" +
            "   \"Simple sum:\"" +
            "       simpleSum: double = 5.0 + 6.0" +
            "}";

        [TestMethod]
        public void SimpleComputedSumTest()
        {
            FormNode node = QLParserHelper.Parse(_simpleComputedForm);

            var computed = node.Children
                .Where(x => x.Type == NodeType.COMPUTED)
                .Select(x => x as ComputedNode)
                .First();

            Assert.AreEqual(NodeType.COMPUTED, computed.Type);
            Assert.AreEqual("simpleSum", computed.ID);
            Assert.AreEqual("Simple sum:", computed.Text);
            Assert.AreEqual(QValueType.DOUBLE, computed.ValueType);
            Assert.AreEqual(NodeType.ARTHIMETRIC_EXPRESSION, computed.Expression.GetNodeType());
            Assert.AreEqual(QValueType.DOUBLE, computed.Expression.GetQValueType());
        }
    }
}