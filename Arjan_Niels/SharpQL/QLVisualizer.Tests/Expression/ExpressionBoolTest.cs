using Microsoft.VisualStudio.TestTools.UnitTesting;
using QLVizualizer.Expression.Types;

namespace QLVisualizer.Tests.Expression
{
    [TestClass]
    public class ExpressionBoolTest
    {
        private ExpressionBool _expression1;
        private ExpressionBool _expression2;
        private ExpressionBool _expression3;

        [TestInitialize]
        public void Initialize()
        {
            _expression1 = new ExpressionBool(new string[0], () => true);
            _expression2 = new ExpressionBool(new string[0], () => true);
            _expression3 = new ExpressionBool(new string[0], () => false);
        }

        [TestMethod]
        public void AlterationTest()
        {
            // Create inital combination
            ExpressionBool expression = _expression1.Combine(_expression2, ExpressionOperator.And) as ExpressionBool;
            Assert.IsTrue(expression.Result);

            // Alter combined object
            _expression2 = _expression2.Combine(_expression3, ExpressionOperator.And) as ExpressionBool;
            Assert.IsTrue(expression.Result);
        }

        [TestMethod]
        public void AndTest()
        {
            // true && true
            ExpressionBool expression = _expression1.Combine(_expression2, ExpressionOperator.And) as ExpressionBool;
            Assert.IsTrue(expression.Result);

            // true && true && false
            expression = expression.Combine(_expression3, ExpressionOperator.And) as ExpressionBool;
            Assert.IsFalse(expression.Result);
        }

        [TestMethod]
        public void OrTest()
        {
            // true || true
            ExpressionBool expression = _expression1.Combine(_expression2, ExpressionOperator.Or) as ExpressionBool;
            Assert.IsTrue(expression.Result);

            // true || true || false
            expression = expression.Combine(_expression3, ExpressionOperator.Or) as ExpressionBool;
            Assert.IsTrue(expression.Result);
        }

        [TestMethod]
        public void EqualsTest()
        {
            // true == true
            ExpressionBool expression = _expression1.Combine(_expression2, ExpressionOperator.Equals) as ExpressionBool;
            Assert.IsTrue(expression.Result);

            // (true == true) == false
            expression = expression.Combine(_expression3, ExpressionOperator.Equals) as ExpressionBool;
            Assert.IsFalse(expression.Result);
        }
    }
}
