using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using QL_Vizualizer.Expression.Types;

namespace QL_Visualizer.Tests.Expression
{
    [TestClass]
    public class ExpressionIntTest
    {
        private ExpressionInt _expression1;
        private ExpressionInt _expression2;
        private ExpressionInt _expression3;

        [TestInitialize]
        public void Initialize()
        {
            _expression1 = new ExpressionInt(new string[0], () => 1);
            _expression2 = new ExpressionInt(new string[0], () => 2);
            _expression3 = new ExpressionInt(new string[0], () => 3);
        }

        [TestMethod]
        public void AlterationTest()
        {
            // Create inital combination
            ExpressionInt expression = _expression1.Combine(_expression2, ExpressionOperator.Plus) as ExpressionInt;
            Assert.AreEqual(expression.Execute(), 3);

            // Alter combined object
            _expression2 = _expression2.Combine(_expression3, ExpressionOperator.Plus) as ExpressionInt;
            Assert.AreEqual(expression.Execute(), 3);
        }

        [TestMethod]
        public void PlusTest()
        {
            ExpressionInt expression = _expression1.Combine(_expression2, ExpressionOperator.Plus) as ExpressionInt;

            // 1 + 2
            Assert.AreEqual(expression.Execute(), 3);

            expression = expression.Combine(_expression3, ExpressionOperator.Plus) as ExpressionInt;

            // 1 + 2 + 3
            Assert.AreEqual(expression.Execute(), 6);
        }

        [TestMethod]
        public void MinusTest()
        {
            ExpressionInt expression = _expression1.Combine(_expression2, ExpressionOperator.Minus) as ExpressionInt;

            // 1 - 2
            Assert.AreEqual(expression.Execute(), -1);

            expression = expression.Combine(_expression3, ExpressionOperator.Minus) as ExpressionInt;

            // 1 - 2 - 3
            Assert.AreEqual(expression.Execute(), -4);
        }

        [TestMethod]
        public void DivideTest()
        {
            ExpressionInt expression = _expression1.Combine(_expression2, ExpressionOperator.Divide) as ExpressionInt;

            // 1 / 2
            Assert.AreEqual(expression.Execute(), 0);

            expression = expression.Combine(_expression3, ExpressionOperator.Divide) as ExpressionInt;

            // (1 / 2) / 3
            Assert.AreEqual(expression.Execute(), 0);
        }

        [TestMethod]
        public void MultiplyTest()
        {
            ExpressionInt expression = _expression1.Combine(_expression2, ExpressionOperator.Multiply) as ExpressionInt;

            // 1 * 2
            Assert.AreEqual(expression.Execute(), 2);

            expression = expression.Combine(_expression3, ExpressionOperator.Multiply) as ExpressionInt;

            // 1 * 2 * 3
            Assert.AreEqual(expression.Execute(), 6);
        }

        [TestMethod]
        public void IntToDoubleCastTest()
        {
            ExpressionDouble expression = _expression1;
            Assert.AreEqual(expression.Execute(), 1);
        }

        [TestMethod]
        public void DoubleToIntCastTest()
        {
            ExpressionInt expression = _expression1;
            Assert.AreEqual(expression.Execute(), 1);
        }

        [TestMethod]
        public void IntGreaterThan()
        {
            Assert.IsFalse((_expression1.Compare(_expression2, ExpressionOperator.GreaterThan) as ExpressionBool).Execute());
            Assert.IsTrue((_expression2.Compare(_expression1, ExpressionOperator.GreaterThan) as ExpressionBool).Execute());
        }

        [TestMethod]
        public void IntGreaterEqualsTest()
        {
            Assert.IsFalse((_expression1.Compare(_expression2, ExpressionOperator.GreaterEquals) as ExpressionBool).Execute());
            Assert.IsTrue((_expression2.Compare(_expression1, ExpressionOperator.GreaterEquals) as ExpressionBool).Execute());
        }

        [TestMethod]
        public void IntLessEqualsTest()
        {
            Assert.IsTrue((_expression1.Compare(_expression2, ExpressionOperator.LessEquals) as ExpressionBool).Execute());
            Assert.IsFalse((_expression2.Compare(_expression1, ExpressionOperator.LessEquals) as ExpressionBool).Execute());
        }

        [TestMethod]
        public void IntLessThanTest()
        {
            Assert.IsTrue((_expression1.Compare(_expression2, ExpressionOperator.LessThan) as ExpressionBool).Execute());
            Assert.IsFalse((_expression2.Compare(_expression1, ExpressionOperator.LessThan) as ExpressionBool).Execute());
        }

        [TestMethod]
        public void IntEqualsTest()
        {
            Assert.IsFalse((_expression1.Compare(_expression2, ExpressionOperator.Equals) as ExpressionBool).Execute());
        }
    }
}
