using Microsoft.VisualStudio.TestTools.UnitTesting;
using QLParser.AST.QL;
using System.Linq;

namespace QLParser.Tests.QL
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
                .Where(x => x.Type == NodeType.Computed)
                .Select(x => x as ComputedNode)
                .First();

            Assert.AreEqual(NodeType.Computed, computed.Type);
            Assert.AreEqual("simpleSum", computed.ID);
            Assert.AreEqual("Simple sum:", computed.Text);
            Assert.AreEqual(QValueType.Double, computed.ValueType);
            Assert.AreEqual(NodeType.ArthimetricExpression, computed.Expression.GetNodeType());
            Assert.AreEqual(QValueType.Double, computed.Expression.GetQValueType());
        }
    }
}