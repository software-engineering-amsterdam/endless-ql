using Microsoft.VisualStudio.TestTools.UnitTesting;
using QLVisualizer;
using QLVisualizer.Expression.Enums;
using QLVisualizer.Expression.Types;
using QLVisualizer.Expression.Types.Numeric;

namespace QL_Visualizer.Tests.Expression
{
    [TestClass]
    public class ExpressionHexTest
    {
        private ExpressionHex _expression1;
        private ExpressionHex _expression2;
        private ExpressionHex _expression3;

        [TestInitialize]
        public void Initialize()
        {
            _expression1 = new ExpressionHex(new string[0], () => new Hexadecimal(1));
            _expression2 = new ExpressionHex(new string[0], () => new Hexadecimal(2));
            _expression3 = new ExpressionHex(new string[0], () => new Hexadecimal(3));
        }

        [TestMethod]
        public void AlterationTest()
        {
            // Create inital combination
            ExpressionHex expression = _expression1.Combine(_expression2, ExpressionOperator.Plus) as ExpressionHex;
            Assert.AreEqual(expression.Result.ToInteger(), 3);

            // Alter combined object
            _expression2 = _expression2.Combine(_expression3, ExpressionOperator.Plus) as ExpressionHex;
            Assert.AreEqual(expression.Result.ToInteger(), 3);
        }

        [TestMethod]
        public void PlusTest()
        {
            ExpressionHex expression = _expression1.Combine(_expression2, ExpressionOperator.Plus) as ExpressionHex;

            // 1 + 2
            Assert.AreEqual(expression.Result.ToInteger(), 3);

            expression = expression.Combine(_expression3, ExpressionOperator.Plus) as ExpressionHex;

            // 1 + 2 + 3
            Assert.AreEqual(expression.Result.ToInteger(), 6);
        }

        [TestMethod]
        public void MinusTest()
        {
            ExpressionHex expression = _expression1.Combine(_expression2, ExpressionOperator.Minus) as ExpressionHex;

            // 1 - 2
            Assert.AreEqual(expression.Result.ToInteger(), -1);

            expression = expression.Combine(_expression3, ExpressionOperator.Minus) as ExpressionHex;

            // 1 - 2 - 3
            Assert.AreEqual(expression.Result.ToInteger(), -4);
        }

        [TestMethod]
        public void DivideTest()
        {
            ExpressionHex expression = _expression1.Combine(_expression2, ExpressionOperator.Divide) as ExpressionHex;

            // 1 / 2
            Assert.AreEqual(expression.Result.ToInteger(), 0);

            expression = expression.Combine(_expression3, ExpressionOperator.Divide) as ExpressionHex;

            // (1 / 2) / 3
            Assert.AreEqual(expression.Result.ToInteger(), 0);
        }

        [TestMethod]
        public void MultiplyTest()
        {
            ExpressionHex expression = _expression1.Combine(_expression2, ExpressionOperator.Multiply) as ExpressionHex;

            // 1 * 2
            Assert.AreEqual(expression.Result.ToInteger(), 2);

            expression = expression.Combine(_expression3, ExpressionOperator.Multiply) as ExpressionHex;

            // 1 * 2 * 3
            Assert.AreEqual(expression.Result.ToInteger(), 6);
        }

        [TestMethod]
        public void ToDoubleTest()
        {
            ExpressionDouble expression = _expression1.ToDoubleExpression();
            Assert.AreEqual(expression.Result, 1);
        }

        [TestMethod]
        public void HexGreaterThan()
        {
            Assert.IsFalse((_expression1.Compare(_expression2, ExpressionOperator.GreaterThan) as ExpressionBool).Result);
            Assert.IsTrue((_expression2.Compare(_expression1, ExpressionOperator.GreaterThan) as ExpressionBool).Result);
        }

        [TestMethod]
        public void HexGreaterEqualsTest()
        {
            Assert.IsFalse((_expression1.Compare(_expression2, ExpressionOperator.GreaterEquals) as ExpressionBool).Result);
            Assert.IsTrue((_expression2.Compare(_expression1, ExpressionOperator.GreaterEquals) as ExpressionBool).Result);
        }

        [TestMethod]
        public void HexLessEqualsTest()
        {
            Assert.IsTrue((_expression1.Compare(_expression2, ExpressionOperator.LessEquals) as ExpressionBool).Result);
            Assert.IsFalse((_expression2.Compare(_expression1, ExpressionOperator.LessEquals) as ExpressionBool).Result);
        }

        [TestMethod]
        public void HexLessThanTest()
        {
            Assert.IsTrue((_expression1.Compare(_expression2, ExpressionOperator.LessThan) as ExpressionBool).Result);
            Assert.IsFalse((_expression2.Compare(_expression1, ExpressionOperator.LessThan) as ExpressionBool).Result);
        }

        [TestMethod]
        public void HexEqualsTest()
        {
            Assert.IsFalse((_expression1.Compare(_expression2, ExpressionOperator.Equals) as ExpressionBool).Result);
        }
    }
}
