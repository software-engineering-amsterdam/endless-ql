using Microsoft.VisualStudio.TestTools.UnitTesting;
using QL_Vizualizer.Expression;
using QL_Vizualizer.Expression.Types;

namespace QL_Visualizer.Tests.Expression
{
    [TestClass]
    public class ExpressionTest
    {
        [TestMethod]
        public void BooleanExpression()
        {
            ExpressionBool expression =  new ExpressionBool(new string[0],() => { return true; });
            ExpressionBool expression1 = new ExpressionBool(new string[0],() => { return true; });
            ExpressionBool expression2 = new ExpressionBool(new string[0],() => { return true; });
            ExpressionBool expression3 = new ExpressionBool(new string[0],() => { return false; });
            
            // True
            Assert.IsTrue(expression.Execute());

            // False
            Assert.IsFalse(expression3.Execute());

            // True && True
            expression = expression.Combine(expression1, ExpressionOperator.And) as ExpressionBool;
            Assert.IsTrue(expression.Execute());

            // Must be true after object alteration
            expression1 = new ExpressionBool(new string[0], () => false);
            Assert.IsTrue(expression.Execute());
            
            // True && True && True
            expression = expression.Combine(expression2, ExpressionOperator.And) as ExpressionBool;
            Assert.IsTrue(expression.Execute());

            // True && True && True && False
            expression = expression.Combine(expression3, ExpressionOperator.And) as ExpressionBool;
            Assert.IsFalse(expression.Execute());
        }

        [TestMethod]
        public void IntegerExpression()
        {
            ExpressionInt expression =  new ExpressionInt(new string[0], () => { return 1; });
            ExpressionInt expression1 = new ExpressionInt(new string[0], () => { return 2; });
            ExpressionInt expression2 = new ExpressionInt(new string[0], () => { return 3; });
            ExpressionInt expression3 = new ExpressionInt(new string[0], () => { return 4; });

            // 1
            Assert.AreEqual(expression.Execute(), 1);

            // 1 + 2
            expression = expression.Combine(expression1, ExpressionOperator.Plus) as ExpressionInt;
            Assert.AreEqual(expression.Execute(), 1 + 2);

            // Must be true after object alteration
            expression1 = new ExpressionInt(new string[0], () => 100);
            Assert.AreEqual(expression.Execute(), 1 + 2);


            // 1 + 2 + 3
            expression = expression.Combine(expression2, ExpressionOperator.Plus) as ExpressionInt;
            Assert.AreEqual(expression.Execute(), 1 + 2 + 3);

            // 1 + 2 + 3 + 4
            expression = expression.Combine(expression3, ExpressionOperator.Plus) as ExpressionInt;
            Assert.AreEqual(expression.Execute(), 1 + 2 + 3 + 4);
        }

        [TestMethod]
        public void DoubleExpression()
        {
            ExpressionDouble expression =  new ExpressionDouble(new string[0], () => { return 1.1; });
            ExpressionDouble expression1 = new ExpressionDouble(new string[0], () => { return 2.2; });
            ExpressionDouble expression2 = new ExpressionDouble(new string[0], () => { return 3.3; });
            ExpressionDouble expression3 = new ExpressionDouble(new string[0], () => { return 4.4; });

            // 1.1
            Assert.AreEqual(expression.Execute(), 1.1);

            // 1.1 + 2.2
            expression = expression.Combine(expression1, ExpressionOperator.Plus) as ExpressionDouble;
            Assert.AreEqual(expression.Execute(), 1.1 + 2.2);

            // Must be true after object alteration
            expression1 = new ExpressionDouble(new string[0], () => 100.001);
            Assert.AreEqual(expression.Execute(), 1.1 + 2.2);

            // 1.1 + 2.2 + 3.3
            expression = expression.Combine(expression2, ExpressionOperator.Plus) as ExpressionDouble;
            Assert.AreEqual(expression.Execute(), 1.1 + 2.2 + 3.3);

            // 1.1 + 2.2 + 3.3 + 4.4
            expression = expression.Combine(expression3, ExpressionOperator.Plus) as ExpressionDouble;
            Assert.AreEqual(expression.Execute(), 1.1 + 2.2 + 3.3 + 4.4);
        }
    }
}
