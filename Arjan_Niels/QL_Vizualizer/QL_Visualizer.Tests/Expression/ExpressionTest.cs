using Microsoft.VisualStudio.TestTools.UnitTesting;
using QL_Vizualizer.Expression;

namespace QL_Visualizer.Tests.Expression
{
    [TestClass]
    public class ExpressionTest
    {
        [TestMethod]
        public void BooleanExpression()
        {
            IExpression<bool> expression = new ExpressionLeaf<bool>(() => { return true; });
            IExpression<bool> expression1 = new ExpressionLeaf<bool>(() => { return true; });
            IExpression<bool> expression2 = new ExpressionLeaf<bool>(() => { return true; });
            IExpression<bool> expression3 = new ExpressionLeaf<bool>(() => { return false; });
            
            // True
            Assert.IsTrue(expression.Validate());

            // False
            Assert.IsFalse(expression3.Validate());

            // True && True
            expression = expression.Add(expression1);
            Assert.IsTrue(expression.Validate());

            // True && True && True
            expression = expression.Add(expression2);
            Assert.IsTrue(expression.Validate());

            // True && True && True && False
            expression = expression.Add(expression3);
            Assert.IsFalse(expression.Validate());
        }

        [TestMethod]
        public void IntegerExpression()
        {
            IExpression<int> expression =  new ExpressionLeaf<int>(() => { return 1; });
            IExpression<int> expression1 = new ExpressionLeaf<int>(() => { return 2; });
            IExpression<int> expression2 = new ExpressionLeaf<int>(() => { return 3; });
            IExpression<int> expression3 = new ExpressionLeaf<int>(() => { return 4; });

            // 1
            Assert.AreEqual(expression.Validate(), 1);

            // 1 + 2
            expression = expression.Add(expression1);
            Assert.AreEqual(expression.Validate(), 1 + 2);

            // 1 + 2 + 3
            expression = expression.Add(expression2);
            Assert.AreEqual(expression.Validate(), 1 + 2 + 3);

            // 1 + 2 + 3 + 4
            expression = expression.Add(expression3);
            Assert.AreEqual(expression.Validate(), 1 + 2 + 3 + 4);
        }

        [TestMethod]
        public void DoubleExpression()
        {
            IExpression<double> expression =  new ExpressionLeaf<double>(() => { return 1.1; });
            IExpression<double> expression1 = new ExpressionLeaf<double>(() => { return 2.2; });
            IExpression<double> expression2 = new ExpressionLeaf<double>(() => { return 3.3; });
            IExpression<double> expression3 = new ExpressionLeaf<double>(() => { return 4.4; });

            // 1.1
            Assert.AreEqual(expression.Validate(), 1.1);

            // 1.1 + 2.2
            expression = expression.Add(expression1);
            Assert.AreEqual(expression.Validate(), 1.1 + 2.2);

            // 1.1 + 2.2 + 3.3
            expression = expression.Add(expression2);
            Assert.AreEqual(expression.Validate(), 1.1 + 2.2 + 3.3);

            // 1.1 + 2.2 + 3.3 + 4.4
            expression = expression.Add(expression3);
            Assert.AreEqual(expression.Validate(), 1.1 + 2.2 + 3.3 + 4.4);
        }
    }
}
