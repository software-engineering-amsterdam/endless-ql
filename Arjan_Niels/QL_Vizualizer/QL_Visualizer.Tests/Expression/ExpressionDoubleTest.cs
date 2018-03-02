using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using QL_Vizualizer.Expression.Types;

namespace QL_Visualizer.Tests.Expression
{
    [TestClass]
    public class ExpressionDoubleTest
    {
        private ExpressionDouble _expression1;
        private ExpressionDouble _expression2;
        private ExpressionDouble _expression3;

        [TestInitialize]
        public void Initialize()
        {
            _expression1 = new ExpressionDouble(new string[0], () => 1);
            _expression2 = new ExpressionDouble(new string[0], () => 2);
            _expression3 = new ExpressionDouble(new string[0], () => 3);
        }

        [TestMethod]
        public void AlterationTest()
        {
            // Create inital combination
            ExpressionDouble expression = _expression1.Combine(_expression2, ExpressionOperator.Plus) as ExpressionDouble;
            Assert.AreEqual(expression.Execute(), 3);

            // Alter combined object
            _expression2 = _expression2.Combine(_expression3, ExpressionOperator.Plus) as ExpressionDouble;
            Assert.AreEqual(expression.Execute(), 3);
        }

        [TestMethod]
        public void PlusTest()
        {
            ExpressionDouble expression = _expression1.Combine(_expression2, ExpressionOperator.Plus) as ExpressionDouble;

            // 1 + 2
            Assert.AreEqual(expression.Execute(), 3);

            expression = expression.Combine(_expression3, ExpressionOperator.Plus) as ExpressionDouble;

            // 1 + 2 + 3
            Assert.AreEqual(expression.Execute(), 6);
        }

        [TestMethod]
        public void MinusTest()
        {
            ExpressionDouble expression = _expression1.Combine(_expression2, ExpressionOperator.Minus) as ExpressionDouble;

            // 1 - 2
            Assert.AreEqual(expression.Execute(), -1);

            expression = expression.Combine(_expression3, ExpressionOperator.Minus) as ExpressionDouble;

            // 1 - 2 - 3
            Assert.AreEqual(expression.Execute(), -4);
        }

        [TestMethod]
        public void DivideTest()
        {
            ExpressionDouble expression = _expression1.Combine(_expression2, ExpressionOperator.Divide) as ExpressionDouble;

            // 1 / 2
            Assert.AreEqual(expression.Execute(), 0.5);

            expression = expression.Combine(_expression3, ExpressionOperator.Divide) as ExpressionDouble;

            // (1 / 2) / 3
            Assert.AreEqual(expression.Execute(), 0.5 / 3);
        }

        [TestMethod]
        public void MultiplyTest()
        {
            ExpressionDouble expression = _expression1.Combine(_expression2, ExpressionOperator.Multiply) as ExpressionDouble;

            // 1 * 2
            Assert.AreEqual(expression.Execute(), 2);

            expression = expression.Combine(_expression3, ExpressionOperator.Multiply) as ExpressionDouble;

            // 1 * 2 * 3
            Assert.AreEqual(expression.Execute(), 6);
        }

        [TestMethod]
        public void DoubleToIntCastTest()
        {
            ExpressionInt expression = _expression1;
            Assert.AreEqual(expression.Execute(), 1);
        }

        [TestMethod]
        public void DoubleGreaterThan()
        {
            Assert.IsFalse((_expression1.Compare(_expression2, ExpressionOperator.GreaterThan) as ExpressionBool).Execute());
            Assert.IsTrue((_expression2.Compare(_expression1, ExpressionOperator.GreaterThan) as ExpressionBool).Execute()); 
        }

        [TestMethod]
        public void DoubleGreaterEqualsTest()
        {
            Assert.IsFalse((_expression1.Compare(_expression2, ExpressionOperator.GreaterEquals) as ExpressionBool).Execute());
            Assert.IsTrue((_expression2.Compare(_expression1, ExpressionOperator.GreaterEquals) as ExpressionBool).Execute());
        }

        [TestMethod]
        public void DoubleLessEqualsTest()
        {
            Assert.IsTrue((_expression1.Compare(_expression2, ExpressionOperator.LessEquals) as ExpressionBool).Execute());
            Assert.IsFalse((_expression2.Compare(_expression1, ExpressionOperator.LessEquals) as ExpressionBool).Execute());
        }

        [TestMethod]
        public void DoubleLessThanTest()
        {
            Assert.IsTrue((_expression1.Compare(_expression2, ExpressionOperator.LessThan) as ExpressionBool).Execute());
            Assert.IsFalse((_expression2.Compare(_expression1, ExpressionOperator.LessThan) as ExpressionBool).Execute());
        }

        [TestMethod]
        public void DoubleEqualsTest()
        {
            Assert.IsFalse((_expression1.Compare(_expression2, ExpressionOperator.Equals) as ExpressionBool).Execute());
        }


    }
}
