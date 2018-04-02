using Microsoft.VisualStudio.TestTools.UnitTesting;
using QLVisualizer.Expression.Enums;
using QLVisualizer.Expression.Types;

namespace QL_Visualizer.Tests.Expression
{
    [TestClass]
    public class ExpressionTextTest
    {
        private ExpressionText _expression1;
        private ExpressionText _expression2;
        private ExpressionText _expression3;

        [TestInitialize]
        public void Initialize()
        {
            _expression1 = new ExpressionText(new string[0], () => "1");
            _expression2 = new ExpressionText(new string[0], () => "2");
            _expression3 = new ExpressionText(new string[0], () => "3");
        }

        [TestMethod]
        public void AlterationTest()
        {
            // Create inital combination
            ExpressionText expression = _expression1.Combine(_expression2, ExpressionOperator.Plus) as ExpressionText;
            Assert.AreEqual(expression.Result, "12");

            // Alter combined object
            _expression2 = _expression2.Combine(_expression3, ExpressionOperator.Plus) as ExpressionText;
            Assert.AreEqual(expression.Result, "12");
        }

        [TestMethod]
        public void PlusTest()
        {
            ExpressionText expression = _expression1.Combine(_expression2, ExpressionOperator.Plus) as ExpressionText;

            // 1 + 2
            Assert.AreEqual(expression.Result, "12");

            expression = expression.Combine(_expression3, ExpressionOperator.Plus) as ExpressionText;

            // 1 + 2 + 3
            Assert.AreEqual(expression.Result, "123");
        }

        [TestMethod]
        public void TextEqualsTest()
        {
            Assert.IsFalse((_expression1.Compare(_expression2, ExpressionOperator.Equals) as ExpressionBool).Result);
        }
    }
}
